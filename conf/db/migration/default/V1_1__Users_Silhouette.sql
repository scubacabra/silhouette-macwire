create table users
(
    user_id    varchar not null primary key,
    first_name varchar,
    last_name  varchar,
    full_name  varchar,
    email      varchar,
    avatar_url varchar
);

CREATE TABLE login_info
(
    id bigserial primary key,
    provider_id  varchar not null,
    provider_key varchar not null
);

CREATE TABLE user_login_info
(
    user_id varchar not null,
    login_info_id bigint not null
);

CREATE TABLE password_info
(
    hasher   varchar not null,
    password varchar not null,
    salt     varchar,
    login_info_id bigint not null
);

CREATE TABLE oauth1_info
(
    id bigserial primary key,
    token  varchar not null,
    secret varchar not null,
    login_info_id bigint not null
);

CREATE TABLE oauth2_info
(
    id bigserial  primary key,
    access_token  varchar not null,
    token_type    varchar,
    expires_in    integer,
    refresh_token varchar,
    login_info_id bigint not null
);

CREATE TABLE open_id_info
(
    id varchar not null primary key,
    login_info_id bigint not null
);

CREATE TABLE open_id_attributes
(
    id varchar not null,
    key varchar not null,
    value varchar not null
);
