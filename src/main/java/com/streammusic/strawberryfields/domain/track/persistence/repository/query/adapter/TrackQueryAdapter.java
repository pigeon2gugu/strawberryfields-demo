package com.streammusic.strawberryfields.domain.track.persistence.repository.query.adapter;

import static com.streammusic.strawberryfields.domain.track.persistence.domain.QTrack.track;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.persistence.repository.TrackJpaRepository;
import com.streammusic.strawberryfields.domain.track.persistence.repository.query.TrackQueryRepository;
import com.streammusic.strawberryfields.domain.track.service.dto.TrackListDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TrackQueryAdapter implements TrackQueryRepository {
	private final JPAQueryFactory queryFactory;
	private final TrackJpaRepository trackJpaRepository;

	@Override
	public Page<TrackListDto.Response> findAllByUser(Pageable pageable, User user) {
		JPAQuery<?> essentialQuery =
			queryFactory
				.from(track)
				.where(
					track.user.eq(user))
				.orderBy(track.createdAt.desc());

		List<TrackListDto.Response> result =
			essentialQuery
				.select(
					Projections.fields(
						TrackListDto.Response.class,
						track.id.as("id"),
						track.title.as("title"),
						track.user.artist.as("artist")))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = essentialQuery.clone().select(track.countDistinct());

		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
	}

	@Override
	public Optional<Track> findById(Long id) {
		return trackJpaRepository.findById(id);
	}
}
