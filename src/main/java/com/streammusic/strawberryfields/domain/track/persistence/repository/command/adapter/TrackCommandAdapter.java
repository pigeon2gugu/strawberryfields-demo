package com.streammusic.strawberryfields.domain.track.persistence.repository.command.adapter;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.persistence.repository.TrackJpaRepository;
import com.streammusic.strawberryfields.domain.track.persistence.repository.command.TrackCommandRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TrackCommandAdapter implements TrackCommandRepository {
	private final TrackJpaRepository trackJpaRepository;

	@Override
	public Track save(Track track) {

		return trackJpaRepository.save(track);
	}
}
