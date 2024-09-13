package com.streammusic.strawberryfields.domain.track.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TrackListDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Response {
		private Long id;
		private String title;
		private String artist;
	}
}
