DROP TABLE IF EXISTS tracks;
CREATE TABLE tracks
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT       NOT NULL COMMENT '사용자 ID',
    title      VARCHAR(16)  NOT NULL COMMENT '곡 제목',
    file_data  LONGBLOB     NOT NULL COMMENT '파일',
    created_by BIGINT       NOT NULL COMMENT '생성자',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_by BIGINT       NOT NULL COMMENT '수정자',
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    deleted_by BIGINT                DEFAULT NULL COMMENT '삭제자',
    deleted_at TIMESTAMP             DEFAULT NULL COMMENT '삭제일시'
) engine = InnoDB COMMENT '트랙';