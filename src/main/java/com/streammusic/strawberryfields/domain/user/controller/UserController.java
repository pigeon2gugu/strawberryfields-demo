package com.streammusic.strawberryfields.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.user.service.command.UserCommandService;
import com.streammusic.strawberryfields.domain.user.service.dto.CheckDuplicationDto;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpAgencyDto;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpComposerDto;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.common.ApiResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@RestController
public class UserController {
	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;

	@PostMapping("/composer")
	public ApiResult<SignUpComposerDto.Response> signUpComposer(
		@RequestBody SignUpComposerDto.Request request) {

		return ApiResult.ok(userCommandService.signUpComposer(request));
	}

	@PostMapping("/agency")
	public ApiResult<SignUpAgencyDto.Response> signUpAgency(
		@RequestBody SignUpAgencyDto.Request request) {

		return ApiResult.ok(userCommandService.signUpAgency(request));
	}

	@GetMapping("/email-exists")
	public ApiResult<CheckDuplicationDto.Response> checkEmailDuplication(
		@RequestParam String email) {
		return ApiResult.ok(
			userQueryService.checkDuplication(
				email, userQueryService::isDuplicatedEmail));
	}

	@GetMapping("/artist-exists")
	public ApiResult<CheckDuplicationDto.Response> checkArtistDuplication(
		@RequestParam String artist) {
		return ApiResult.ok(
			userQueryService.checkDuplication(
				artist, userQueryService::isDuplicatedArtist));
	}

	@GetMapping("/company-exists")
	public ApiResult<CheckDuplicationDto.Response> checkCompanyDuplication(
		@RequestParam String company) {
		return ApiResult.ok(
			userQueryService.checkDuplication(
				company, userQueryService::isDuplicatedCompany));
	}

}
