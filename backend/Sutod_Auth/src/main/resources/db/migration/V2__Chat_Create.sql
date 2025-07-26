CREATE TABLE IF NOT EXISTS app_schema.chat(
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    user2_id BIGINT NOT NULL,

    CONSTRAINT uq_users_pair UNIQUE (user_id, user2_id),
    FOREIGN KEY (user_id) REFERENCES app_schema.users(id) ON DELETE CASCADE,
    FOREIGN KEY (user2_id) REFERENCES app_schema.users(id) ON DELETE CASCADE
);

