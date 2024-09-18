package com.streammusic.strawberryfields.domain.auth.service;

import static com.streammusic.strawberryfields.global.exception.resultcode.UnauthorizedExceptionCode.*;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;
import com.streammusic.strawberryfields.domain.auth.service.command.RefreshTokenCommandService;
import com.streammusic.strawberryfields.domain.auth.service.dto.LoginDto;
import com.streammusic.strawberryfields.domain.auth.service.dto.RegenerateTokenDto;
import com.streammusic.strawberryfields.domain.auth.service.qeury.RefreshTokenQueryService;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.exception.UnauthorizedException;
import com.streammusic.strawberryfields.global.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserQueryService userQueryService;
	private final RefreshTokenCommandService refreshTokenCommandService;
	private final RefreshTokenQueryService refreshTokenQueryService;
	private final PasswordEncoder passwordEncoder;

	public LoginDto.Response login(LoginDto.Request loginRequest) {

		User user = userQueryService.getByEmail(loginRequest.email());
		validatePassword(user, loginRequest.password());

		return new LoginDto.Response(
			jwtTokenProvider.generateAccessTokenValue(user.getId()),
			refreshTokenCommandService.createAndSaveRefreshToken(user).getRefreshTokenValue(),
			user.getId());
	}

	public RegenerateTokenDto.Response regenerateToken(String refreshTokenValue) {

		validateRefreshToken(refreshTokenValue);
		Optional<RefreshToken> refreshToken = refreshTokenQueryService.findByRefreshTokenValue(refreshTokenValue);

		User user = userQueryService.getById(refreshToken.get().getUser().getId());

		refreshTokenCommandService.delete(refreshTokenValue);

		return new RegenerateTokenDto.Response(
			jwtTokenProvider.generateAccessTokenValue(user.getId()),
			refreshTokenCommandService.createAndSaveRefreshToken(user).getRefreshTokenValue()
		);
	}

	private void validatePassword(User user, String password) {
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UnauthorizedException(LOGIN_FAILED);
		}
	}

	private void validateRefreshToken(String refreshTokenValue) {
		if (!refreshTokenQueryService.existsByRefreshTokenValue(refreshTokenValue)) {
			throw new UnauthorizedException(UNAUTHORIZED_REFRESH_TOKEN);
		}
	}
}
