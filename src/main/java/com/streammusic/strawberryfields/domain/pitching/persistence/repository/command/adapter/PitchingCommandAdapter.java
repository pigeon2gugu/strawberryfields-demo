package com.streammusic.strawberryfields.domain.pitching.persistence.repository.command.adapter;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.PitchingJpaRepository;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.command.PitchingCommandRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PitchingCommandAdapter implements PitchingCommandRepository {
	private final PitchingJpaRepository pitchingJpaRepository;

	@Override
	public Pitching save(Pitching pitching) {
		return pitchingJpaRepository.save(pitching);
	}
}
