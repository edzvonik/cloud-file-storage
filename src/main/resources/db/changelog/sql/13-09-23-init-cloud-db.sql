--liquibase formatted sql

--changeSet edzvonik:1
CREATE TABLE USERS (
    id INT PRIMARY KEY,
    name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE ROLE (
    id INT PRIMARY KEY,
    role VARCHAR NOT NULL
);

CREATE TABLE USERS_ROLES (
    user_id INT NOT NULL,
    role_id INT NOT NULL
);