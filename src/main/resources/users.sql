CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       roles VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password, roles)
VALUES
    ('admin', '$2a$10$7qkR3Fp1l6Qz3fz6i4Ce4eZx6wRLwzF7Jv5R3NQF1O0xQ8pQ0oQ4G', 'ROLE_ADMIN,ROLE_USER'),
    ('user',  '$2a$10$K8zv5Mht9S/3Y/JT7R0ZNe9qX5uH2E1C/bwR2NqZq5rV1sB1dCkzC', 'ROLE_USER');