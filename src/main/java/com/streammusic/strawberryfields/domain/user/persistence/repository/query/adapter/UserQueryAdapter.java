package com.streammusic.strawberryfields.domain.user.persistence.repository.query.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.persistence.repository.UserJpaRepository;
import com.streammusic.strawberryfields.domain.user.persistence.repository.query.UserQueryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryRepository {
	private final UserJpaRepository userJpaRepository;

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
