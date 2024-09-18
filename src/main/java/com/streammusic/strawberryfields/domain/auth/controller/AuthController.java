package com.streammusic.strawberryfields.domain.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.auth.service.AuthService;
import com.streammusic.strawberryfields.domain.auth.service.dto.LoginDto;
import com.streammusic.strawberryfields.domain.auth.service.dto.RegenerateTokenDto;
import com.streammusic.strawberryfields.global.common.ApiResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Validated
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	public ApiResult<LoginDto.Response> login(@RequestBody @Valid LoginDto.Request loginRequest) {
		return ApiResult.ok(authService.login(loginRequest));
	}

	@GetMapping("/refresh")
	public ApiResult<RegenerateTokenDto.Response> regenerateToken(
		@RequestHeader("refreshToken") String refreshToken) {

		return ApiResult.ok(authService.regenerateToken(refreshToken));
	}
}
