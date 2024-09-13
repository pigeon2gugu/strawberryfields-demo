package com.streammusic.strawberryfields.global.exception.resultcode;

import com.streammusic.strawberryfields.global.exception.ResultCodeProvider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessExceptionCode implements ResultCodeProvider {
	UNSUPPORTED_ACCESS_TOKEN("지원되지 않은 엑세스 토큰입니다."),
	UNSUPPORTED_PROVIDER_TYPE("지원되지 않은 프로바이더 타입입니다."),
	EMPTY_JWT_CLAIMS_STRING("JWT 클레임 문자열이 비어 있습니다."),
	INVALID_SUBJECT_FORMAT("JWT 주체의 형식이 유효하지 않습니다."),
	INVALID_TRACK_TYPE("지원되지 않는 파일 형식입니다. MP3 또는 WAV 파일만 가능합니다."),
	NO_FILE_UPLOADED("파일이 업로드되지 않았거나 비어 있습니다."),
	FILE_UPLOAD_FAILED("파일 업로드에 실패했습니다.");

	private final String message;
}
