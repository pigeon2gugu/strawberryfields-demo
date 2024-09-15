package com.streammusic.strawberryfields.domain.pitching.persistence.repository.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.service.dto.AgencyPitchingListDto;
import com.streammusic.strawberryfields.domain.pitching.service.dto.ComposerPitchingListDto;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface PitchingQueryRepository {
	Optional<Pitching> findByTrackAndUser(Track track, User agency);

	Page<AgencyPitchingListDto.Response> findAllByAgency(Pageable pageable, User agency);

	Page<ComposerPitchingListDto.Response> findAllByComposer(Pageable pageable, User composer);
}
