CREATE TABLE vehicles (
    id SERIAL PRIMARY KEY,
    model_number VARCHAR(255),
    registration_number VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_user_vehicle
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE SET NULL
);
