package com.streammusic.strawberryfields.domain.auth.service.dto;

import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {

	public record Request(@NotBlank String email, String password) {
	}

	public record Response(String accessToken, String refreshToken, Long userId, Role role) {
	}
}
