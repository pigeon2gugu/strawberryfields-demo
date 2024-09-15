package com.streammusic.strawberryfields.domain.pitching.service.dto;

import java.time.Instant;

import com.streammusic.strawberryfields.global.util.TimeUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AgencyPitchingListDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Response {
		private Long id;
		private String title;
		private String artist;
		private String createdAt;

		public Response(Long id, String title, String artist, Instant createdAtUtc) {
			this.id = id;
			this.title = title;
			this.artist = artist;
			this.createdAt = createdAtUtc.toString();
		}

		public Response(Long id, String title, String artist, Instant createdAtUtc, String timeZone) {
			this.id = id;
			this.title = title;
			this.artist = artist;
			this.createdAt = TimeUtil.convertUtcToTimeZone(createdAtUtc, timeZone);
		}
	}
}
