package com.streammusic.strawberryfields.global.config;

public class SecurityConstants {
	public static final String[] EXCLUDE_URLS = {
		"/api/v1/user",
		"/api/v1/user/email-exists",
		"/resources/**",
		"/static/**",
		"/actuator/**",
		"/swagger-ui/**",
		"/swagger-resources/**"
	};
}
