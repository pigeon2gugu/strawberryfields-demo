package com.streammusic.strawberryfields.domain.user.persistence.repository.query;

import java.util.Optional;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;

public interface UserQueryRepository {
	Optional<User> findById(Long userId);

	Optional<User> findByIdAndRole(Long userId, Role role);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

}
