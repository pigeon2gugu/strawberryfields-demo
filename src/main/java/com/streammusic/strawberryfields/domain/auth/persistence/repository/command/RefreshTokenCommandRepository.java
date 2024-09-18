package com.streammusic.strawberryfields.domain.auth.persistence.repository.command;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;

public interface RefreshTokenCommandRepository {

	RefreshToken save(RefreshToken refreshToken);
}
