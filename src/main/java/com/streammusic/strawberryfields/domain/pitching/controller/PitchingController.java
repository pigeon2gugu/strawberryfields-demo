package com.streammusic.strawberryfields.domain.pitching.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.pitching.service.command.PitchingCommandService;
import com.streammusic.strawberryfields.domain.pitching.service.dto.CreatePitchingDto;
import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;
import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.security.Authenticated;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/composer/pitching")
@RestController
public class PitchingController {
	private final PitchingCommandService pitchingCommandService;

	@PostMapping()
	public ApiResult<CreatePitchingDto.Response> createPitching(@RequestBody CreatePitchingDto.Request request,
		@Authenticated RequestUser requestUser) {

		return ApiResult.ok(pitchingCommandService.createPitching(request, requestUser.getUserId()));
	}

}
