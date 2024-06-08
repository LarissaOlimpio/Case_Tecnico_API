CREATE TABLE enrollment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    course_id BIGINT,
    enrollment_date TIMESTAMP NOT NULL,
    UNIQUE (user_id, course_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

