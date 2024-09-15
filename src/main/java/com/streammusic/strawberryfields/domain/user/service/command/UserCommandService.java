package com.streammusic.strawberryfields.domain.user.service.command;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.persistence.repository.command.UserCommandRepository;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpComposerDto;
import com.streammusic.strawberryfields.domain.user.service.dto.SignUpAgencyDto;
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

	public SignUpComposerDto.Response signUpComposer(SignUpComposerDto.Request request) {

		validateEmailDuplicate(request.email());
		validateArtistDuplicate(request.artist());
		User user = createComposerUser(request);

		return new SignUpComposerDto.Response(user.getId(), user.getEmail());
	}

	public SignUpAgencyDto.Response signUpAgency(SignUpAgencyDto.Request request) {

		validateEmailDuplicate(request.email());
		validateCompanyDuplicate(request.company());
		User user = createAgencyUser(request);

		return new SignUpAgencyDto.Response(user.getId(), user.getEmail());
	}

	private void validateEmailDuplicate(String email) {
		if (userQueryService.isDuplicatedEmail(email)) {
			throw new DuplicatedException(DuplicatedResourceCode.USER_EMAIL);
		}
	}

	private void validateCompanyDuplicate(String company) {
		if (userQueryService.isDuplicatedCompany(company)) {
			throw new DuplicatedException(DuplicatedResourceCode.USER_COMPANY);
		}
	}

	private void validateArtistDuplicate(String artist) {
		if (userQueryService.isDuplicatedArtist(artist)) {
			throw new DuplicatedException(DuplicatedResourceCode.USER_ARTIST);
		}
	}

	private User createComposerUser(SignUpComposerDto.Request request) {

		User user = User.createOf(
			request.email(),
			passwordEncoder.encode(request.password()),
			Role.COMPOSER,
			request.artist(),
			null);

		return userCommandRepository.save(user);
	}

	private User createAgencyUser(SignUpAgencyDto.Request request) {

		User user = User.createOf(
			request.email(),
			passwordEncoder.encode(request.password()),
			Role.AGENCY,
			null,
			request.company());

		return userCommandRepository.save(user);
	}
}
