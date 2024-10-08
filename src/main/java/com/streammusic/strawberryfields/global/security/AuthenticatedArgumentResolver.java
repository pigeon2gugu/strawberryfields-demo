package com.streammusic.strawberryfields.global.security;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.streammusic.strawberryfields.domain.auth.service.AuthenticatedUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthenticatedArgumentResolver implements HandlerMethodArgumentResolver {
	private final AuthenticatedUserService authenticatedUserService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Authenticated.class);
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory)
		throws Exception {
		return authenticatedUserService.getRequestUser();
	}
}
