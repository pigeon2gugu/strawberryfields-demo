package com.streammusic.strawberryfields.domain.auth.service.command;

import java.security.SecureRandom;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.streammusic.strawberryfields.domain.auth.persistence.domain.RefreshToken;
import com.streammusic.strawberryfields.domain.auth.persistence.repository.command.RefreshTokenCommandRepository;
import com.streammusic.strawberryfields.domain.auth.service.qeury.RefreshTokenQueryService;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenCommandService {
	@Value("${security.refresh-token.length-byte}")
	private int tokenByteLength;

	@Value("${security.refresh-token.expiry-value}")
	private long tokenExpiryValue;

	@Value("${security.refresh-token.expiry-unit}")
	private String rawTokenExpiryUnit;

	private ChronoUnit tokenExpiryUnit;

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	private final RefreshTokenCommandRepository refreshTokenCommandRepository;
	private final RefreshTokenQueryService refreshTokenQueryService;

	@PostConstruct
	public void init() {
		try {
			tokenExpiryUnit = ChronoUnit.valueOf(rawTokenExpiryUnit.toUpperCase());
		} catch (IllegalArgumentException e) {
			tokenExpiryUnit = ChronoUnit.DAYS;
		}
	}

	@Transactional
	public RefreshToken createAndSaveRefreshToken(User user) {
		return refreshTokenCommandRepository.save(
			RefreshToken.createOf(
				user,
				generateUniqueRefreshTokenValue(),
				tokenExpiryValue,
				tokenExpiryUnit));
	}

	private String generateUniqueRefreshTokenValue() {
		String tokenValue;
		do {
			tokenValue = generateNewRefreshTokenValue();
		} while (refreshTokenQueryService.existsByRefreshTokenValue(tokenValue));

		return tokenValue;
	}

	private String generateNewRefreshTokenValue() {
		byte[] randomBytes = new byte[tokenByteLength];
		secureRandom.nextBytes(randomBytes);

		return base64Encoder.encodeToString(randomBytes);
	}

	@Transactional
	public void delete(String refreshTokenValue) {

		refreshTokenQueryService
			.findByRefreshTokenValue(refreshTokenValue)
			.ifPresent(RefreshToken::expireRefreshToken);
	}
}
