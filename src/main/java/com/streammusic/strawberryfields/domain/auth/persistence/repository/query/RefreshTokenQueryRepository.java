package com.streammusic.strawberryfields.domain.auth.persistence.repository.query;

import java.util.Optional;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;

public interface RefreshTokenQueryRepository {
	Boolean existsByRefreshTokenValue(String refreshTokenValue);

	Optional<RefreshToken> findByUserIdAndRefreshTokenValue(Long userId, String refreshTokenValue);
}
