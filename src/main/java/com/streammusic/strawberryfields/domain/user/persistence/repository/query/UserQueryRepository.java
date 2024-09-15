package com.streammusic.strawberryfields.domain.user.persistence.repository.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.domain.user.service.dto.AgencyListDto;

public interface UserQueryRepository {
	Optional<User> findById(Long userId);

	Optional<User> findByIdAndRole(Long userId, Role role);

	Optional<User> findByEmail(String email);

	Page<AgencyListDto.Response> findAllAgency(Pageable pageable);

	boolean existsByEmail(String email);

	boolean existsByArtist(String artist);

	boolean existsByCompany(String company);

}
