drop database if exists grouptalkdb;
create database grouptalkdb;

use grouptalkdb;

CREATE TABLE users (
    id BINARY(16) NOT NULL,
    loginid VARCHAR(15) NOT NULL UNIQUE,
    password BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    userid BINARY(16) NOT NULL,
    role ENUM ('registered','admin'),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (userid, role)
);

CREATE TABLE auth_tokens (
    userid BINARY(16) NOT NULL,
    token BINARY(16) NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (token)
);

CREATE TABLE groups (
    id BINARY(16) NOT NULL,
    creator BINARY(16) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    comment BINARY(16) NOT NULL,
    FOREIGN KEY (creator) REFERENCES users(id) on delete cascade,
    FOREIGN KEY (comment) REFERENCES comments(id) on delete cascade,
    PRIMARY KEY (id)
);

CREATE TABLE comments(
    id BINARY(16) NOT NULL,
    title VARCHAR(100) NOT NULL,
    creator BINARY(16) NOT NULL,
    comment VARCHAR(500) NOT NULL,
    last_modified TIMESTAMP NOT NULL,
    creation_timestamp DATETIME not null default current_timestamp,
    FOREIGN KEY (creator) REFERENCES users(id) on delete cascade,
    PRIMARY KEY (id)
);

CREATE TABLE added_groups(
    userid BINARY(16) NOT NULL,
    groupid BINARY(16) NOT NULL,
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    FOREIGN KEY (groupid) REFERENCES groups(id) on delete cascade,
    PRIMARY KEY (userid, groupid)
)

