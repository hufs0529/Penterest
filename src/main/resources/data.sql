-- 권한 데이터 삽입
INSERT INTO authority (authority_name) VALUES ('NORMAL');
INSERT INTO authority (authority_name) VALUES ('ADMIN');

-- 회원 데이터 삽입
INSERT INTO member (email, password) VALUES ('user@example.com', 'encoded_password1');
INSERT INTO member (email, password) VALUES ('admin@example.com', 'encoded_password2');

-- 회원 권한 매핑 데이터 삽입
INSERT INTO user_authority (member_id, authority_name) VALUES (1, 'NORMAL');
INSERT INTO user_authority (member_id, authority_name) VALUES (2, 'ADMIN');
