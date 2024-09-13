package com.streammusic.strawberryfields.domain.track.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.track.persistence.repository.query.TrackQueryRepository;
import com.streammusic.strawberryfields.domain.track.service.dto.TrackListDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackQueryService {
	private final TrackQueryRepository trackQueryRepository;
	private final UserQueryService userQueryService;

	public Page<TrackListDto.Response> getTracks(Pageable pageable, Long userId) {
		User user = userQueryService.getById(userId);

		return trackQueryRepository.findAllByUser(pageable, user);
	}
}
