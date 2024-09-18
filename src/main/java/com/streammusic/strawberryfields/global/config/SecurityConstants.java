package com.streammusic.strawberryfields.global.config;

public class SecurityConstants {
	public static final String[] EXCLUDE_URLS = {
		"/api/v1/user/composer",
		"/api/v1/user/agency",
		"/api/v1/user/email-exists",
		"/api/v1/user/company-exists",
		"/api/v1/user/artist-exists",
		"/api/v1/auth/login",
		"/api/v1/auth/refresh",
		"/resources/**",
		"/static/**",
		"/actuator/**",
		"/swagger-ui/**",
		"/swagger-resources/**"
	};
}
