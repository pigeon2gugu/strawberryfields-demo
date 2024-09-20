package com.streammusic.strawberryfields.global.security.filter;

import static com.streammusic.strawberryfields.global.exception.resultcode.BusinessExceptionCode.*;
import static com.streammusic.strawberryfields.global.exception.resultcode.UnauthorizedExceptionCode.*;
import static org.springframework.util.StringUtils.*;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.config.SecurityConstants;
import com.streammusic.strawberryfields.global.exception.BusinessException;
import com.streammusic.strawberryfields.global.exception.UnauthorizedException;
import com.streammusic.strawberryfields.global.security.AuthenticatedUser;
import com.streammusic.strawberryfields.global.security.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtTokenProvider jwtTokenProvider;
	private final UserQueryService userQueryService;
	private final FilterExceptionHandler filterExceptionHandler;
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(
		HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		String path = request.getRequestURI();
		if (isExcludedPath(path)) {
			filterChain.doFilter(request, response);
			return;
		}

		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		try {
			extractToken(authorization)
				.ifPresentOrElse(
					jwtToken -> {
						jwtTokenProvider.validateToken(jwtToken); // 토큰 검증

						Authentication authentication =
							getAuthentication(jwtTokenProvider.getSubject(jwtToken));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					},
					() -> {
						throw new UnauthorizedException(MISSING_AUTHORIZATION_HEADER);
					});

			filterChain.doFilter(request, response);

		} catch (UnauthorizedException exception) {
			filterExceptionHandler.handleException(response, HttpStatus.UNAUTHORIZED, exception);
		} catch (BusinessException exception) {
			filterExceptionHandler.handleException(response, HttpStatus.BAD_REQUEST, exception);
		} catch (Exception exception) {
			filterExceptionHandler.handleException(response, HttpStatus.INTERNAL_SERVER_ERROR, exception);
		}

	}

	private boolean isExcludedPath(String path) {
		for (String excludedPath : SecurityConstants.EXCLUDE_URLS) {
			if (pathMatcher.match(excludedPath, path)) {
				return true;
			}
		}
		return false;
	}

	private Optional<String> extractToken(String authorization) { // resolve AccessToken
		if (hasText(authorization) && Pattern.matches("^Bearer .*", authorization)) {
			String value = authorization.replaceAll("^Bearer( )*", "");

			return hasText(value) ? Optional.of(value) : Optional.empty();
		}

		return Optional.empty();
	}

	private Authentication getAuthentication(String subject) {
		long userId;
		try {
			userId = Long.parseLong(subject);
		} catch (NumberFormatException e) {
			throw new BusinessException(INVALID_SUBJECT_FORMAT);
		}

		User user = userQueryService.getById(userId);
		AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

		return new UsernamePasswordAuthenticationToken(
			authenticatedUser, null, authenticatedUser.getAuthorities());
	}
}
