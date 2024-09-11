package com.streammusic.strawberryfields.domain.user.persistence.repository.command.adapter;

import org.springframework.stereotype.Repository;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.repository.UserJpaRepository;
import com.streammusic.strawberryfields.domain.user.persistence.repository.command.UserCommandRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserCommandAdapter implements UserCommandRepository {
	private final UserJpaRepository userJpaRepository;

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}
