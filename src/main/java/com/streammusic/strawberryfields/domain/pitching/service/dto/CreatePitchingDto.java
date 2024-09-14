package com.streammusic.strawberryfields.domain.pitching.service.dto;

import lombok.NonNull;

public class CreatePitchingDto {
	public record Request(@NonNull Long trackId, @NonNull Long agencyId, String description) {

	}

	public record Response(Long id) {
	}
}
