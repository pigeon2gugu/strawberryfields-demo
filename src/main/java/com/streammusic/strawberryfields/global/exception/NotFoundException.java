package com.streammusic.strawberryfields.global.exception;

import com.streammusic.strawberryfields.global.exception.resultcode.NotFoundResourceCode;

public class NotFoundException extends CustomException {

	public NotFoundException(NotFoundResourceCode code) {
		super(new NotFoundResult(code));
	}

	public NotFoundException(NotFoundResourceCode code, Throwable cause) {
		super(new NotFoundResult(code), cause);
	}

	private record NotFoundResult(NotFoundResourceCode code) implements ResultCodeProvider {
		@Override
		public String getMessage() {
			return String.format("%s 리소스를 찾을 수 없습니다.", code.getMessage());
		}

		@Override
		public String getCode() {
			return "NOT_FOUND_" + code.name();
		}
	}
}
