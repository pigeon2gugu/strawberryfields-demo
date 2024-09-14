package com.streammusic.strawberryfields.domain.pitching.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface PitchingJpaRepository extends JpaRepository<Pitching, Long> {

	Pitching save(Pitching pitching);

	Optional<Pitching> findByTrackAndUser(Track track, User agency);

}
