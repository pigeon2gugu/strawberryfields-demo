package com.streammusic.strawberryfields.global.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	public static String convertUtcToTimeZone(Instant utcInstant, String timeZone) {
		if (utcInstant == null || timeZone == null) {
			return null;
		}

		ZonedDateTime zonedDateTime = utcInstant.atZone(ZoneId.of(timeZone));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return zonedDateTime.format(formatter);
	}
}

