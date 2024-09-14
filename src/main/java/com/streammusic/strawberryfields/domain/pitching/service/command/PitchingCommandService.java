package com.streammusic.strawberryfields.domain.pitching.service.command;

import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.enums.PitchingStatus;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.command.PitchingCommandRepository;
import com.streammusic.strawberryfields.domain.pitching.service.dto.CreatePitchingDto;
import com.streammusic.strawberryfields.domain.pitching.service.query.PitchingQueryService;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.service.query.TrackQueryService;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.exception.DuplicatedException;
import com.streammusic.strawberryfields.global.exception.ForbiddenException;
import com.streammusic.strawberryfields.global.exception.resultcode.DuplicatedResourceCode;
import com.streammusic.strawberryfields.global.exception.resultcode.ForbiddenExceptionCode;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PitchingCommandService {
	private final PitchingCommandRepository pitchingCommandRepository;
	private final PitchingQueryService pitchingQueryService;
	private final UserQueryService userQueryService;
	private final TrackQueryService trackQueryService;

	public CreatePitchingDto.Response createPitching(CreatePitchingDto.Request request, Long userId) {
		User composer = userQueryService.getByIdAndRole(userId, Role.COMPOSER);
		User agency = userQueryService.getByIdAndRole(request.agencyId(), Role.AGENCY);
		Track track = trackQueryService.getById(request.trackId());

		validateTrack(track, composer);
		checkDuplicationPitching(track, agency);
		Pitching pitching = savePitching(request, track, agency);

		return new CreatePitchingDto.Response(pitching.getId());
	}

	private void validateTrack(Track track, User user) {
		if (track.getUser() != user) {
			throw new ForbiddenException(ForbiddenExceptionCode.INVALID_TRACK_OWNER);
		}
	}

	private void checkDuplicationPitching(Track track, User agency) {
		if (pitchingQueryService.findByTrackAndAgency(track, agency).isPresent()) {
			throw new DuplicatedException(DuplicatedResourceCode.PITCHING_REQUEST);
		}
	}

	private Pitching savePitching(CreatePitchingDto.Request request, Track track, User agency) {
		Pitching pitching = Pitching.createOf(
			track,
			agency,
			request.description(),
			PitchingStatus.PENDING
		);

		return pitchingCommandRepository.save(pitching);
	}
}
