package com.streammusic.strawberryfields.domain.pitching.persistence.domain;

import static jakarta.persistence.FetchType.*;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLRestriction;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.pitching.persistence.enums.PitchingStatus;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.global.common.entity.AbstractJpaPersistable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
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
	name = "pitching",
	indexes = {})
@AttributeOverride(name = "id", column = @Column(name = "id"))
@Entity
@SQLRestriction("deleted_at is null")
public class Pitching extends AbstractJpaPersistable {

	@Comment("트랙 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "track_id")
	private Track track;

	@Comment("기획사 유저 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "agency_id")
	private User user;

	@Comment("설명")
	@Column(name = "description", length = 500, nullable = true)
	private String description;

	@Comment("피칭 상태")
	@Column(name = "status", length = 16, nullable = false)
	@Enumerated(EnumType.STRING)
	private PitchingStatus status;

	public static Pitching createOf(
		Track track,
		User user,
		String description,
		PitchingStatus status) {

		return new Pitching(
			track,
			user,
			description,
			status
		);
	}
}
