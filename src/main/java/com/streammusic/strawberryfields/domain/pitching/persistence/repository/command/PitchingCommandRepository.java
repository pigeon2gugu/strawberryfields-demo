package com.streammusic.strawberryfields.domain.pitching.persistence.repository.command;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;

public interface PitchingCommandRepository {
	Pitching save(Pitching pitching);
}
