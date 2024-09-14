package com.streammusic.strawberryfields.global.exception;

import com.streammusic.strawberryfields.global.exception.resultcode.DuplicatedResourceCode;

public class DuplicatedException extends CustomException {

	public DuplicatedException(DuplicatedResourceCode code) {
		super(new DuplicatedResult(code));
	}

	public DuplicatedException(DuplicatedResourceCode code, Throwable cause) {
		super(new DuplicatedResult(code), cause);
	}

	private record DuplicatedResult(DuplicatedResourceCode code) implements ResultCodeProvider {
		@Override
		public String getMessage() {
			return String.format("%s 이(가) 중복됩니다.", code.getMessage());
		}

		@Override
		public String getCode() {
			return "DUPLICATED_" + code.name();
		}
	}
}
