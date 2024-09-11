package com.streammusic.strawberryfields.domain.user.persistence.repository.query;

import java.util.Optional;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface UserQueryRepository {
	Optional<User> findById(Long userId);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

}
