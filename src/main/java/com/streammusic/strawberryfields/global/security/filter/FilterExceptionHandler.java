package com.streammusic.strawberryfields.global.security.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.exception.CustomException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilterExceptionHandler {

	private final ObjectMapper objectMapper;

	public void handleException(HttpServletResponse response, HttpStatus status, CustomException exception) throws
		IOException {
		response.setStatus(status.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		log.error(exception.getMessage(), exception);
		response.getWriter().write(objectMapper.writeValueAsString(ApiResult.unAuthorized(exception)));
	}

	public void handleException(HttpServletResponse response, HttpStatus status, Exception exception) throws
		IOException {
		response.setStatus(status.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		log.error(exception.getMessage(), exception);
		response.getWriter().write(objectMapper.writeValueAsString(ApiResult.badRequest(exception.getMessage())));
	}
}
