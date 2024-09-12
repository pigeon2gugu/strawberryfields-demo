package com.streammusic.strawberryfields.global.exception;

import com.streammusic.strawberryfields.global.exception.resultcode.UnauthorizedExceptionCode;

public class UnauthorizedException extends CustomException {
	public UnauthorizedException(UnauthorizedExceptionCode code) {
		super(code);
	}
}
