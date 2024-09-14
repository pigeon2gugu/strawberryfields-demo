package com.streammusic.strawberryfields.domain.pitching.service.query;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.query.PitchingQueryRepository;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PitchingQueryService {
	private final PitchingQueryRepository pitchingQueryRepository;

	public Optional<Pitching> findByTrackAndAgency(Track track, User agency) {
		
		return pitchingQueryRepository.findByTrackAndUser(track, agency);
	}
}
