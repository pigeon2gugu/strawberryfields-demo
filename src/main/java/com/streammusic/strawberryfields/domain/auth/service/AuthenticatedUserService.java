package com.streammusic.strawberryfields.domain.auth.service;

import static com.streammusic.strawberryfields.global.exception.resultcode.BusinessExceptionCode.*;
import static com.streammusic.strawberryfields.global.exception.resultcode.UnauthorizedExceptionCode.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;
import com.streammusic.strawberryfields.global.exception.BusinessException;
import com.streammusic.strawberryfields.global.exception.UnauthorizedException;

@Service
public class AuthenticatedUserService {
	public RequestUser getRequestUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			throw new UnauthorizedException(MISSING_AUTHORIZATION_HEADER) {
			};
		}

		if (!(authentication.getPrincipal() instanceof RequestUser)) {
			throw new BusinessException(INVALID_SUBJECT_FORMAT);
		}

		return (RequestUser)authentication.getPrincipal();
	}

	public RequestUser getUser() {

		return getRequestUser();
	}
}
