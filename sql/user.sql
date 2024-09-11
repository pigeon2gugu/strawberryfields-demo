DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(64)  NOT NULL COMMENT '이메일',
    password   VARCHAR(255) NOT NULL COMMENT '비밀번호',
    role       VARCHAR(16)  NOT NULL COMMENT '역할',
    created_by BIGINT       NOT NULL COMMENT '생성자',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_by BIGINT       NOT NULL COMMENT '수정자',
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    deleted_by BIGINT                DEFAULT NULL COMMENT '삭제자',
    deleted_at TIMESTAMP             DEFAULT NULL COMMENT '삭제일시'
) engine = InnoDB comment '유저';
