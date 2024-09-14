package com.streammusic.strawberryfields.global.exception;

import com.streammusic.strawberryfields.global.exception.resultcode.ForbiddenExceptionCode;

public class ForbiddenException extends CustomException {
	public ForbiddenException(ForbiddenExceptionCode code) {
		super(code);
	}
}
