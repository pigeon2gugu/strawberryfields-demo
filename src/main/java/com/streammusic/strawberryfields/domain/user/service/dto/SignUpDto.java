package com.streammusic.strawberryfields.domain.user.service.dto;

import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;

import lombok.NonNull;

public class SignUpDto {
	public record Request(
		@NonNull String email,
		@NonNull String password,
		@NonNull Role role) {
	}

	public record Response(Long userId, String email) {
	}
}
