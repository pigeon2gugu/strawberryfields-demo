package com.streammusic.strawberryfields.domain.auth.persistence.repository.query.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.auth.persistence.RefreshTokenJpaRepository;
import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;
import com.streammusic.strawberryfields.domain.auth.persistence.repository.query.RefreshTokenQueryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenQueryAdapter implements RefreshTokenQueryRepository {

	private final RefreshTokenJpaRepository refreshTokenJpaRepository;

	@Override
	public Boolean existsByRefreshTokenValue(String refreshTokenValue) {

		return refreshTokenJpaRepository.existsByRefreshTokenValue(refreshTokenValue);
	}

	@Override
	public Optional<RefreshToken> findByUserIdAndRefreshTokenValue(Long userId, String refreshTokenValue) {

		return refreshTokenJpaRepository.findByUserIdAndRefreshTokenValue(
			userId, refreshTokenValue);
	}

}
