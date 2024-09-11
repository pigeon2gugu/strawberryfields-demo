package com.streammusic.strawberryfields.global.exception;

public interface ResultCodeProvider {
	String getMessage();

	default String getCode() {
		if (this instanceof Enum<?>) {
			return ((Enum<?>)this).name();
		}

		return null;
	}
}
