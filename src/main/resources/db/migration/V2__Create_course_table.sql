CREATE TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) NOT NULL,
    instructor_username VARCHAR(20),
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    inactivation_date TIMESTAMP,
    UNIQUE (code),
    FOREIGN KEY (instructor_username) REFERENCES users(username)
);
