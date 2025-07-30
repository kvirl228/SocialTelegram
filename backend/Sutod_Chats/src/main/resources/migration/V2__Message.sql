CREATE TABLE IF NOT EXISTS app_schema.messages(
    id SERIAL PRIMARY KEY,
    c_message VARCHAR NOT NULL,
    c_senderId BIGINT NOT NULL,
    c_chatId BIGINT NOT NULL,
    c_dateTime TIMESTAMP NOT NULL
)