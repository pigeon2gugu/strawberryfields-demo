package com.streammusic.strawberryfields.domain.user.persistence.repository.command;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;

public interface UserCommandRepository {
	User save(User user);
}
