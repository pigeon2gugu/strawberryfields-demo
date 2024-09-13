package com.streammusic.strawberryfields.domain.track.persistence.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streammusic.strawberryfields.domain.track.service.dto.TrackListDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface TrackQueryRepository {
	Page<TrackListDto.Response> findAllByUser(Pageable pageable, User user);
}