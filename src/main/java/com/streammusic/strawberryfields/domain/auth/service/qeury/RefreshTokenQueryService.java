package com.streammusic.strawberryfields.domain.auth.service.qeury;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;
import com.streammusic.strawberryfields.domain.auth.persistence.repository.query.RefreshTokenQueryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenQueryService {
	private final RefreshTokenQueryRepository refreshTokenQueryRepository;

	public boolean existsByRefreshTokenValue(String refreshTokenValue) {

		return refreshTokenQueryRepository.existsByRefreshTokenValue(refreshTokenValue);
	}

	public Optional<RefreshToken> findByRefreshTokenValue(String refreshTokenValue) {

		return refreshTokenQueryRepository.findByRefreshTokenValue(refreshTokenValue);
	}

}
