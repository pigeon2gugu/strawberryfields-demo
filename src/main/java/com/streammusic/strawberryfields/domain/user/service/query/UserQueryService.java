package com.streammusic.strawberryfields.domain.user.service.query;

import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.repository.query.UserQueryRepository;
import com.streammusic.strawberryfields.global.exception.NotFoundException;
import com.streammusic.strawberryfields.global.exception.resultcode.NotFoundResourceCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryService {
	private final UserQueryRepository userQueryRepository;

	public User getById(Long userId) {

		return userQueryRepository
			.findById(userId)
			.orElseThrow(() -> new NotFoundException(NotFoundResourceCode.USER));
	}

	public User getByEmail(String email) {

		return userQueryRepository
			.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(NotFoundResourceCode.USER));
	}

	public boolean isDuplicatedEmail(String email) {

		return userQueryRepository.existsByEmail(email);
	}

}
