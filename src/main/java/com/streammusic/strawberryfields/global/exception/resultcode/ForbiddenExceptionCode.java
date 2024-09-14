package com.streammusic.strawberryfields.global.exception.resultcode;

import com.streammusic.strawberryfields.global.exception.ResultCodeProvider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForbiddenExceptionCode implements ResultCodeProvider {
	INVALID_TRACK_OWNER("요청자와 트랙의 소유자가 일치하지 않습니다.");

	private final String message;
}
