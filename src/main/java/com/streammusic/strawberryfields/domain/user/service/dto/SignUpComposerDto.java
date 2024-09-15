package com.streammusic.strawberryfields.domain.user.service.dto;

import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;

import lombok.NonNull;

public class SignUpComposerDto {
	public record Request(
		@NonNull String email,
		@NonNull String password,
		@NonNull String artist) {
	}

	public record Response(Long userId, String email) {
	}
}
