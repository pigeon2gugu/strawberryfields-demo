package com.streammusic.strawberryfields.global.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;

public class AuditorAwareImpl implements AuditorAware<Long> {
	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null
			|| !(authentication.getPrincipal() instanceof RequestUser requestUser)) {
			return Optional.empty();
		}

		return Optional.of(requestUser.getUserId());
	}
}
