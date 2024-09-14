package com.streammusic.strawberryfields.domain.track.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.service.dto.TrackListDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface TrackJpaRepository extends JpaRepository<Track, Long> {

	public Track save(Track track);

	Page<TrackListDto.Response> findAllByUser(Pageable pageable, User user);

}
