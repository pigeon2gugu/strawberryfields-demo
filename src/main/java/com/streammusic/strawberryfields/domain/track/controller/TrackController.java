package com.streammusic.strawberryfields.domain.track.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.streammusic.strawberryfields.domain.track.service.command.TrackCommandService;
import com.streammusic.strawberryfields.domain.track.service.dto.UploadTrackDto;
import com.streammusic.strawberryfields.domain.user.service.dto.RequestUser;
import com.streammusic.strawberryfields.global.common.ApiResult;
import com.streammusic.strawberryfields.global.security.Authenticated;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/composer/track")
@RestController
public class TrackController {
	private final TrackCommandService trackCommandService;

	@PostMapping("/upload")
	public ApiResult<UploadTrackDto.Response> uploadFile(@RequestPart("file") MultipartFile file,
		@Authenticated RequestUser requestUser) {

		return ApiResult.ok(trackCommandService.uploadTrack(file, requestUser.getUserId()));
	}
}
