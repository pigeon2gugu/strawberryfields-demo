package com.streammusic.strawberryfields.domain.pitching.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.pitching.service.command.PitchingCommandService;
import com.streammusic.strawberryfields.domain.pitching.service.dto.ComposerPitchingListDto;
import com.streammusic.strawberryfields.domain.pitching.service.dto.CreatePitchingDto;
import com.streammusic.strawberryfields.domain.pitching.service.query.PitchingQueryService;
import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;
import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.security.Authenticated;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/composer/pitching")
@RestController
public class ComposerPitchingController {
	private final PitchingCommandService pitchingCommandService;
	private final PitchingQueryService pitchingQueryService;

	@PostMapping()
	public ApiResult<CreatePitchingDto.Response> createPitching(@RequestBody CreatePitchingDto.Request request,
		@Authenticated RequestUser requestUser) {

		return ApiResult.ok(pitchingCommandService.createPitching(request, requestUser.getUserId()));
	}

	@GetMapping()
	public ApiResult<Page<ComposerPitchingListDto.Response>> getPitchings(@PageableDefault(size = 10) Pageable pageable,
		@Authenticated RequestUser requestUser) {

		return ApiResult.ok(pitchingQueryService.getComposerPitchings(pageable, requestUser.getUserId()));
	}

}
