package com.streammusic.strawberryfields.domain.user.service.dto;

import lombok.NonNull;

public class SignUpAgencyDto {
	public record Request(
		@NonNull String email,
		@NonNull String password,
		@NonNull String company) {
	}

	public record Response(Long userId, String email) {
	}
}
