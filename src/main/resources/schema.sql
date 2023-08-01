CREATE TABLE IF NOT EXISTS authority (
    authority_name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS member (
    member_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS user_authority (
    member_id BIGINT,
    authority_name VARCHAR(50),
    PRIMARY KEY (member_id, authority_name),
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (authority_name) REFERENCES authority (authority_name)
);
