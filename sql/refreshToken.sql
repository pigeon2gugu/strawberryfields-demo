DROP TABLE IF EXISTS refresh_token;
CREATE TABLE refresh_token
(
    refresh_token_id    BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '리프레시 토큰 ID',
    user_id             BIGINT COMMENT '토큰 소유자 유저 아이디',
    refresh_token_value VARCHAR(50) NOT NULL COMMENT '리프레시 토큰값',
    issued_datetime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '발급일시',
    expired_datetime    TIMESTAMP COMMENT '만료일시',
    INDEX               IDX_REFRESH_TOKEN_VALUE (refresh_token_value)
) ENGINE=InnoDB COMMENT '리프레시 토큰';