package com.streammusic.strawberryfields.global.exception.resultcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DuplicatedResourceCode {
	USER_EMAIL("유저 이메일");

	private final String message;
}
