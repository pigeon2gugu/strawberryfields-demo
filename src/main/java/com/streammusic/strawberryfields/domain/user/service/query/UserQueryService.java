package com.streammusic.strawberryfields.domain.user.service.query;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.persistence.repository.query.UserQueryRepository;
import com.streammusic.strawberryfields.domain.user.service.dto.CheckDuplicationDto;
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

	public User getByIdAndRole(Long userId, Role role) {

		return userQueryRepository
			.findByIdAndRole(userId, role)
			.orElseThrow(() -> new NotFoundException(NotFoundResourceCode.USER));
	}

	public User getByEmail(String email) {

		return userQueryRepository
			.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(NotFoundResourceCode.USER));
	}

	public CheckDuplicationDto.Response checkDuplication(
		String field, Function<String, Boolean> checkFunction) {
		boolean isDuplicate = checkFunction.apply(field);
		CheckDuplicationDto.Response response =
			new CheckDuplicationDto.Response(isDuplicate);
		return response;
	}

	public boolean isDuplicatedEmail(String email) {

		return userQueryRepository.existsByEmail(email);
	}

	public boolean isDuplicatedArtist(String artist) {

		return userQueryRepository.existsByArtist(artist);
	}

	public boolean isDuplicatedCompany(String company) {

		return userQueryRepository.existsByCompany(company);
	}

}
