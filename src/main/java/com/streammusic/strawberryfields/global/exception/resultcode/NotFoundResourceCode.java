package com.streammusic.strawberryfields.global.exception.resultcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotFoundResourceCode {
	USER("유저");

	private final String message;
}