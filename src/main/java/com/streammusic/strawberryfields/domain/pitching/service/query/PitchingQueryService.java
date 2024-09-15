package com.streammusic.strawberryfields.domain.pitching.service.query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.query.PitchingQueryRepository;
import com.streammusic.strawberryfields.domain.pitching.service.dto.AgencyPitchingListDto;
import com.streammusic.strawberryfields.domain.pitching.service.dto.ComposerPitchingListDto;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PitchingQueryService {
	private static final String TIME_ZONE = "Asia/Seoul";

	private final PitchingQueryRepository pitchingQueryRepository;
	private final UserQueryService userQueryService;

	public Optional<Pitching> findByTrackAndAgency(Track track, User agency) {

		return pitchingQueryRepository.findByTrackAndUser(track, agency);
	}

	public Page<AgencyPitchingListDto.Response> getAgencyPitchings(Pageable pageable, Long userId) {
		User agency = userQueryService.getById(userId);
		Page<AgencyPitchingListDto.Response> pitchings = pitchingQueryRepository.findAllByAgency(pageable, agency);

		List<AgencyPitchingListDto.Response> transformedTimeResult = pitchings.getContent().stream()
			.map(response -> new AgencyPitchingListDto.Response(
				response.getId(),
				response.getTitle(),
				response.getArtist(),
				Instant.parse(response.getCreatedAt()),
				TIME_ZONE
			))
			.collect(Collectors.toList());

		return new PageImpl<>(transformedTimeResult, pageable, pitchings.getTotalElements());
	}

	public Page<ComposerPitchingListDto.Response> getComposerPitchings(Pageable pageable, Long userId) {
		User composer = userQueryService.getById(userId);
		Page<ComposerPitchingListDto.Response> pitchings = pitchingQueryRepository.findAllByComposer(pageable,
			composer);

		List<ComposerPitchingListDto.Response> transformedTimeResult = pitchings.getContent().stream()
			.map(response -> new ComposerPitchingListDto.Response(
				response.getId(),
				response.getTitle(),
				response.getCompany(),
				Instant.parse(response.getCreatedAt()),
				TIME_ZONE
			))
			.collect(Collectors.toList());

		return new PageImpl<>(transformedTimeResult, pageable, pitchings.getTotalElements());
	}

}
