CREATE TABLE IF NOT EXISTS app_schema.messange (
    id SERIAL PRIMARY KEY,
    c_chat_id BIGINT NOT NULL,
    c_sender_id BIGINT NOT NULL,
    c_text TEXT NOT NULL,
    c_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (c_chat_id) REFERENCES app_schema.chat(id) ON DELETE CASCADE,
    FOREIGN KEY (c_sender_id) REFERENCES app_schema.users(id) ON DELETE CASCADE
);