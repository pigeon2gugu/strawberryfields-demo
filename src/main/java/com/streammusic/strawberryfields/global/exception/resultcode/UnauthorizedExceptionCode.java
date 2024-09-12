package com.streammusic.strawberryfields.global.exception.resultcode;

import com.streammusic.strawberryfields.global.exception.ResultCodeProvider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnauthorizedExceptionCode implements ResultCodeProvider {
	LOGIN_FAILED("로그인에 실패하였습니다."),
	UNAUTHORIZED_REFRESH_TOKEN("리프레시 토큰값이 다릅니다."),
	EXPIRED_REFRESH_TOKEN("리프레시 토큰이 만료되었습니다."),
	EXPIRED_ACCESS_TOKEN("엑세스 토큰이 만료되었습니다."),
	INVALID_ACCESS_TOKEN("엑세스 토큰이 유효하지 않습니다."),
	MISSING_AUTHORIZATION_HEADER("인증 헤더가 누락되었습니다.");

	private final String message;
}
