package com.streammusic.strawberryfields.global.exception.resultcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DuplicatedResourceCode {
	USER_EMAIL("유저 이메일"),
	USER_ARTIST("유저 아티스트 명"),
	USER_COMPANY("유저 기획사 명"),
	PITCHING_REQUEST("피칭 요청");

	private final String message;
}
