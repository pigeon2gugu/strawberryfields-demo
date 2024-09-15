package com.streammusic.strawberryfields.domain.pitching.service.dto;

import java.time.Instant;

import com.streammusic.strawberryfields.global.util.TimeUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ComposerPitchingListDto {

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Response {
		private Long id;
		private String title;
		private String company;
		private String createdAt;

		public Response(Long id, String title, String company, Instant createdAtUtc) {
			this.id = id;
			this.title = title;
			this.company = company;
			this.createdAt = createdAtUtc.toString();
		}

		public Response(Long id, String title, String company, Instant createdAtUtc, String timeZone) {
			this.id = id;
			this.title = title;
			this.company = company;
			this.createdAt = TimeUtil.convertUtcToTimeZone(createdAtUtc, timeZone);
		}
	}
}

