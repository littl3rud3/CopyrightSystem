CREATE TABLE singer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE recording
(
    id           BIGSERIAL PRIMARY KEY,
    song_code    VARCHAR(32),
    title        VARCHAR(4096),
    version      VARCHAR(128),
    release_time TIMESTAMP NOT NULL DEFAULT NOW(),
    singer_id    BIGINT REFERENCES singer (id)
);

CREATE TABLE company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
--     copyright_id BIGINT REFERENCES copyright(id)
);

CREATE TABLE copyright
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(256),
    expire_dt DATE NOT NULL DEFAULT NOW(),
    cost BIGINT NOT NULL DEFAULT 0,
    company_id BIGINT REFERENCES company(id)
--     recording_id BIGINT REFERENCES recording(id)
);

CREATE TABLE recording_copyright(
    copyright_id BIGINT REFERENCES copyright(id),
    recording_id BIGINT REFERENCES recording(id),
    PRIMARY KEY (copyright_id, recording_id)
);

CREATE TABLE role (
    role_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE client (
    user_id BIGSERIAL PRIMARY KEY,
    login VARCHAR(256) UNIQUE,
    password VARCHAR(256),
    role_id BIGINT NOT NULL DEFAULT 1 REFERENCES role(role_id)
)

