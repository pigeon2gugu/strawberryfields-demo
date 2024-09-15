package com.streammusic.strawberryfields.domain.pitching.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streammusic.strawberryfields.domain.pitching.service.dto.AgencyPitchingListDto;
import com.streammusic.strawberryfields.domain.pitching.service.query.PitchingQueryService;
import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;
import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.security.Authenticated;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/agency/pitching")
@RestController
public class AgencyPitchingController {
	private final PitchingQueryService pitchingQueryService;

	@GetMapping()
	public ApiResult<Page<AgencyPitchingListDto.Response>> getPitchings(@PageableDefault(size = 10) Pageable pageable,
		@Authenticated RequestUser requestUser) {

		return ApiResult.ok(pitchingQueryService.getAgencyPitchings(pageable, requestUser.getUserId()));
	}

}
