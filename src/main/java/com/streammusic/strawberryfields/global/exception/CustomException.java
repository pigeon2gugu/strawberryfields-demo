package com.streammusic.strawberryfields.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final ResultCodeProvider resultCode;
	private final Object data;

	protected CustomException(final ResultCodeProvider resultCode) {
		super(resultCode.getMessage());

		this.resultCode = resultCode;
		this.data = null;
	}

	protected CustomException(final ResultCodeProvider resultCode, final Throwable cause) {
		super(resultCode.getMessage(), cause);

		this.resultCode = resultCode;
		this.data = null;
	}

	protected CustomException(
		final ResultCodeProvider resultCode, final Object data, final Throwable cause) {
		super(resultCode.getMessage(), cause);

		this.resultCode = resultCode;
		this.data = data;
	}
}
