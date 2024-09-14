package com.streammusic.strawberryfields.domain.pitching.persistence.repository.query;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.PitchingJpaRepository;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PitchingQueryAdapter implements PitchingQueryRepository {
	private final PitchingJpaRepository pitchingJpaRepository;

	@Override
	public Optional<Pitching> findByTrackAndUser(Track track, User agency) {
		return pitchingJpaRepository.findByTrackAndUser(track, agency);
	}
}
