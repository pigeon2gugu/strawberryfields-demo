package com.streammusic.strawberryfields.global.exception;

import com.streammusic.strawberryfields.global.exception.resultcode.BusinessExceptionCode;

public class BusinessException extends CustomException {
	public BusinessException(BusinessExceptionCode code) {
		super(code);
	}
}
