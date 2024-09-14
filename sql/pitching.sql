DROP TABLE IF EXISTS pitching;
CREATE TABLE pitching
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    track_id    BIGINT                              NOT NULL,
    agency_id   BIGINT                              NOT NULL,
    description VARCHAR(500) NULL,
    status      ENUM('PENDING', 'ACCEPTED', 'REJECTED') NOT NULL DEFAULT 'PENDING',
    created_by  BIGINT                              NOT NULL COMMENT '생성자',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    updated_by  BIGINT                              NOT NULL COMMENT '수정자',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    deleted_by  BIGINT NULL COMMENT '삭제자',
    deleted_at  TIMESTAMP NULL COMMENT '삭제일시'
) ENGINE=InnoDB COMMENT='피칭';
