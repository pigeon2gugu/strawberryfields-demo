package com.streammusic.strawberryfields.global.exception;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.common.ValidationErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// Custom Exception
	@ResponseStatus(code = OK)
	@ExceptionHandler(SuccessMaskedException.class)
	public ApiResult<?> handleSuccessMaskedException(final SuccessMaskedException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	@ResponseStatus(code = NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ApiResult<?> handleNotFoundException(final NotFoundException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	@ResponseStatus(code = CONFLICT)
	@ExceptionHandler(DuplicatedException.class)
	public ApiResult<?> handleDuplicatedException(final DuplicatedException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	@ResponseStatus(code = UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ApiResult<?> handleUnauthorizedException(final UnauthorizedException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(BusinessException.class)
	public ApiResult<?> handleBusinessException(final BusinessException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	@ResponseStatus(code = FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	public ApiResult<?> handleForbiddenException(final ForbiddenException e) {
		log.error(e.getMessage(), e);

		return ApiResult.of(e);
	}

	// =================================================================================================================================================

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ApiResult<?> handleException(final Exception e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest("오류가 발생하였습니다. 관리자에게 문의하세요.");
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ApiResult<?> handleRuntimeException(final RuntimeException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest("오류가 발생하였습니다. 관리자에게 문의하세요.");
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ApiResult<?> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest(e.getMessage());
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ApiResult<?> handleNoHandlerFoundException(final NoHandlerFoundException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest(e.getMessage());
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(ServletRequestBindingException.class)
	public ApiResult<?> handleServletRequestBindingException(
		final ServletRequestBindingException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest(e.getMessage());
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ApiResult<?> handleBindException(final BindException e) {
		log.error(e.getMessage(), e);

		List<ValidationErrorResponse> validationErrors =
			e.getFieldErrors().stream().map(ValidationErrorResponse::of).toList();

		return ApiResult.badRequest(validationErrors);
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiResult<?> handleHttpMessageNotReadableException(
		final HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest("요청값이 잘못되었습니다.");
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ApiResult<?> handleMissingServletRequestParameterException(
		final MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest(
			List.of(ValidationErrorResponse.createNotNull(e.getParameterName())));
	}

	@ResponseStatus(code = BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ApiResult<?> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e) {
		log.error(e.getMessage(), e);

		return ApiResult.badRequest(List.of(ValidationErrorResponse.of(e)));
	}
}
