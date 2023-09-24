--liquibase formatted sql

--changeSet edzvonik:1
CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE ROLE (
    id INT PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE USERS_ROLES (
    user_id INT NOT NULL,
    role_id INT NOT NULL
);