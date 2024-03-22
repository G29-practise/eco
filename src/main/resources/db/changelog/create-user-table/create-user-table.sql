CREATE TABLE "user"
(
    id           uuid primary key,
    name       VARCHAR   NOT NULL,
    surname       VARCHAR   NOT NULL,
    phone_number VARCHAR   NOT NULL UNIQUE,
    email        VARCHAR UNIQUE,
    password     VARCHAR   NOT NULL,
    address      VARCHAR,
    city         VARCHAR,
    country      VARCHAR,
    past_code    VARCHAR,
    region       VARCHAR,
    is_verify    boolean default false,
    created      TIMESTAMP NOT NULL,
    updated      TIMESTAMP NOT NULL
);