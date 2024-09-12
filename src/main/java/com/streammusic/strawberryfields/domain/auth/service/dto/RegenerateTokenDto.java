package com.streammusic.strawberryfields.domain.auth.service.dto;

public class RegenerateTokenDto {

	public record Response(String accessToken, String refreshToken) {
	}
}
