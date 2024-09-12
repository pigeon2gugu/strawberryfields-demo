package com.streammusic.strawberryfields.domain.auth.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

	public RefreshToken save(RefreshToken refreshToken);

	Boolean existsByRefreshTokenValue(String refreshTokenValue);

	Optional<RefreshToken> findByUserIdAndRefreshTokenValue(Long userId, String refreshTokenValue);
}
