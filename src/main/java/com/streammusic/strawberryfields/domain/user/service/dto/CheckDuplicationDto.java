package com.streammusic.strawberryfields.domain.user.service.dto;

public class CheckDuplicationDto {

	public record Response(boolean isDuplicated) {
	}
}
