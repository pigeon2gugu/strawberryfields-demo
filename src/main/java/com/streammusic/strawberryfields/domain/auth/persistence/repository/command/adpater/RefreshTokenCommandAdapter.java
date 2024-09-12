package com.streammusic.strawberryfields.domain.auth.persistence.repository.command.adpater;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.auth.persistence.RefreshTokenJpaRepository;
import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;
import com.streammusic.strawberryfields.domain.auth.persistence.repository.command.RefreshTokenCommandRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenCommandAdapter implements RefreshTokenCommandRepository {

	private final RefreshTokenJpaRepository refreshTokenJpaRepository;

	@Override
	public RefreshToken save(RefreshToken refreshToken) {

		return refreshTokenJpaRepository.save(refreshToken);
	}
}
