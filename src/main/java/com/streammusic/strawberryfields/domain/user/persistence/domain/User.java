package com.streammusic.strawberryfields.domain.user.persistence.domain;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLRestriction;

import com.streammusic.strawberryfields.domain.user.persistence.enums.Role;
import com.streammusic.strawberryfields.global.common.entity.AbstractJpaPersistable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
	name = "users",
	indexes = {})
@AttributeOverride(name = "id", column = @Column(name = "id"))
@Entity
@SQLRestriction("deleted_at is null")
public class User extends AbstractJpaPersistable {

	@Comment("이메일")
	@Column(name = "email", length = 64, nullable = false)
	private String email;

	@Comment("비밀번호")
	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Comment("역할")
	@Column(name = "role", length = 16, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public static User createOf(
		String email,
		String password,
		Role role) {
		return new User(
			email,
			password,
			role
		);
	}
}
