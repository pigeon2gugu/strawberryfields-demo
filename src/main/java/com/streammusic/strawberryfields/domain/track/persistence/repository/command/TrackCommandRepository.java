package com.streammusic.strawberryfields.domain.track.persistence.repository.command;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;

public interface TrackCommandRepository {
	Track save(Track track);
}
