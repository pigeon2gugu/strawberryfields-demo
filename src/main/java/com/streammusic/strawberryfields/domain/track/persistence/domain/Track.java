package com.streammusic.strawberryfields.domain.track.persistence.domain;

import static jakarta.persistence.FetchType.*;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLRestriction;

import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.global.common.entity.AbstractJpaPersistable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
	name = "tracks",
	indexes = {})
@AttributeOverride(name = "id", column = @Column(name = "id"))
@Entity
@SQLRestriction("deleted_at is null")
public class Track extends AbstractJpaPersistable {

	@Comment("사용자 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Comment("곡 제목")
	@Column(name = "title", length = 16, nullable = false)
	private String title;

	@Comment("파일")
	@Lob
	@Column(name = "file_data", nullable = false)
	private byte[] fileData;

	public static Track createOf(
		User user,
		String title,
		byte[] fileData) {

		return new Track(
			user,
			title,
			fileData
		);
	}
}
