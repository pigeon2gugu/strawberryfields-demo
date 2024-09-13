package com.streammusic.strawberryfields.domain.track.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;

public interface TrackJpaRepository extends JpaRepository<Track, Long> {

	public Track save(Track track);

}
