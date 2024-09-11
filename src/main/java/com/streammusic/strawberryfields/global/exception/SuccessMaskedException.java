package com.streammusic.strawberryfields.global.exception;

public class SuccessMaskedException extends CustomException {
	protected SuccessMaskedException(ResultCodeProvider resultCode, Throwable cause) {
		super(resultCode, cause);
	}
}