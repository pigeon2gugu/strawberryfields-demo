package com.streammusic.strawberryfields.domain.user.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;

public interface UserJpaRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long userId);

	Optional<User> findByIdAndRole(Long userId, Role role);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByCompany(String company);

	boolean existsByArtist(String artist);

	User save(User user);

}
