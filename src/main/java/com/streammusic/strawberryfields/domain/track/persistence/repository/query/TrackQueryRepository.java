package com.streammusic.strawberryfields.domain.track.persistence.repository.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.service.dto.TrackListDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface TrackQueryRepository {
	Page<TrackListDto.Response> findAllByUser(Pageable pageable, User user);

	Optional<Track> findById(Long id);
}