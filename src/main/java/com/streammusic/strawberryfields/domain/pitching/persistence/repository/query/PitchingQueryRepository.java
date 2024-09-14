package com.streammusic.strawberryfields.domain.pitching.persistence.repository.query;

import java.util.Optional;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface PitchingQueryRepository {
	Optional<Pitching> findByTrackAndUser(Track track, User agency);
}
