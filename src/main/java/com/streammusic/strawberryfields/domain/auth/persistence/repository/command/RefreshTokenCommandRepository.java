package com.streammusic.strawberryfields.domain.auth.persistence.repository.command;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;

public interface RefreshTokenCommandRepository {

	public RefreshToken save(RefreshToken refreshToken);
}
