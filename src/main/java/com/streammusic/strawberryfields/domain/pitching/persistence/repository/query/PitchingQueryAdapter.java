package com.streammusic.strawberryfields.domain.pitching.persistence.repository.query;

import static com.streammusic.strawberryfields.domain.pitching.persistence.domain.QPitching.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.streammusic.strawberryfields.domain.pitching.persistence.domain.Pitching;
import com.streammusic.strawberryfields.domain.pitching.persistence.repository.PitchingJpaRepository;
import com.streammusic.strawberryfields.domain.pitching.service.dto.AgencyPitchingListDto;
import com.streammusic.strawberryfields.domain.pitching.service.dto.ComposerPitchingListDto;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PitchingQueryAdapter implements PitchingQueryRepository {
	private final PitchingJpaRepository pitchingJpaRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Pitching> findByTrackAndUser(Track track, User agency) {
		return pitchingJpaRepository.findByTrackAndUser(track, agency);
	}

	@Override
	public Page<AgencyPitchingListDto.Response> findAllByAgency(Pageable pageable, User agency) {
		JPAQuery<?> essentialQuery =
			queryFactory
				.from(pitching)
				.where(
					pitching.user.eq(agency))
				.orderBy(pitching.createdAt.desc());

		List<AgencyPitchingListDto.Response> result =
			essentialQuery
				.select(
					Projections.constructor(
						AgencyPitchingListDto.Response.class,
						pitching.id.as("id"),
						pitching.track.title.as("title"),
						pitching.track.user.artist.as("artist"),
						pitching.createdAt.as("createdAt")))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = essentialQuery.clone().select(pitching.countDistinct());

		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
	}

	@Override
	public Page<ComposerPitchingListDto.Response> findAllByComposer(Pageable pageable, User composer) {
		JPAQuery<?> essentialQuery =
			queryFactory
				.from(pitching)
				.where(
					pitching.track.user.eq(composer))
				.orderBy(pitching.createdAt.desc());

		List<ComposerPitchingListDto.Response> result =
			essentialQuery
				.select(
					Projections.constructor(
						ComposerPitchingListDto.Response.class,
						pitching.id.as("id"),
						pitching.track.title.as("title"),
						pitching.user.company.as("company"),
						pitching.createdAt.as("createdAt")))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = essentialQuery.clone().select(pitching.countDistinct());

		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
	}
}
