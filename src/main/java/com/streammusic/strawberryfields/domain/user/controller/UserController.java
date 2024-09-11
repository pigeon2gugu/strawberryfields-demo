package com.streammusic.strawberryfields.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.user.service.command.UserCommandService;
import com.streammusic.strawberryfields.domain.user.service.dto.CheckDuplicationDto;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpDto;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.common.ApiResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@RestController
public class UserController {
	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;

	//    회원 가입
	@PostMapping()
	public ApiResult<SignUpDto.Response> signUp(
		@RequestBody SignUpDto.Request request) {

		return ApiResult.ok(userCommandService.signUp(request));
	}

	@GetMapping("/email-exists")
	public ApiResult<CheckDuplicationDto.Response> checkEmailDuplication(
		@RequestParam String email) {
		return ApiResult.ok(
			userQueryService.checkDuplication(
				email, userQueryService::isDuplicatedEmail));
	}

}
