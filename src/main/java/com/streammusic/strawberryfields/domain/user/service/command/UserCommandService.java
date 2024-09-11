package com.streammusic.strawberryfields.domain.user.service.command;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.repository.command.UserCommandRepository;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpDto;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.exception.DuplicatedException;
import com.streammusic.strawberryfields.global.exception.resultcode.DuplicatedResourceCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {
	private final UserCommandRepository userCommandRepository;
	private final UserQueryService userQueryService;
	private final PasswordEncoder passwordEncoder;

	// TODO : social signUp
	public SignUpDto.Response signUp(SignUpDto.Request request) {

		validateDuplicate(request);

		User user = createLocalUser(request);
		userCommandRepository.save(user);

		return new SignUpDto.Response(user.getId(), user.getEmail());
	}

	private void validateDuplicate(SignUpDto.Request request) {
		if (userQueryService.isDuplicatedEmail(request.email())) {
			throw new DuplicatedException(DuplicatedResourceCode.USER_EMAIL);
		}
	}

	private User createLocalUser(SignUpDto.Request request) {

		return User.createOf(
			request.email(),
			passwordEncoder.encode(request.password()),
			request.role());
	}
}
