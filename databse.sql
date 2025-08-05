CREATE TABLE rides (
    id SERIAL PRIMARY KEY,
    from_location VARCHAR(255),
    to_location VARCHAR(255),
    date DATE,
    time TIME,
    user_id BIGINT,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);
