package com.streammusic.strawberryfields.domain.user.persistence.repository.query.adapter;

import static com.streammusic.strawberryfields.domain.track.persistence.domain.QTrack.*;
import static com.streammusic.strawberryfields.domain.user.persistence.domain.QUser.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.persistence.repository.UserJpaRepository;
import com.streammusic.strawberryfields.domain.user.persistence.repository.query.UserQueryRepository;
import com.streammusic.strawberryfields.domain.user.service.dto.AgencyListDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryRepository {
	private final UserJpaRepository userJpaRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<User> findById(Long userId) {
		return userJpaRepository.findById(userId);
	}

	@Override
	public Optional<User> findByIdAndRole(Long userId, Role role) {
		return userJpaRepository.findByIdAndRole(userId, role);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userJpaRepository.findByEmail(email);
	}

	@Override
	public Page<AgencyListDto.Response> findAllAgency(Pageable pageable) {
		JPAQuery<?> essentialQuery =
			queryFactory
				.from(user)
				.where(
					user.role.eq(Role.AGENCY))
				.orderBy(user.company.asc());

		List<AgencyListDto.Response> result =
			essentialQuery
				.select(
					Projections.fields(
						AgencyListDto.Response.class,
						user.id.as("id"),
						user.company.as("company")))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> countQuery = essentialQuery.clone().select(user.countDistinct());

		return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userJpaRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByArtist(String artist) {
		return userJpaRepository.existsByArtist(artist);
	}

	@Override
	public boolean existsByCompany(String company) {
		return userJpaRepository.existsByCompany(company);
	}

}
