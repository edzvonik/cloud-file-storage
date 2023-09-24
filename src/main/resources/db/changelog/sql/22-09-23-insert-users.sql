--liquibase formatted sql

--changeSet edzvonik:2
INSERT INTO ROLE (id, name) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- Вставка пользователей в таблицу USERS
INSERT INTO USERS (id, username, password, enabled) VALUES
(1, 'user1', '$2a$10$Zq/qbjAh5pTOGwc1V50koeZNCfCXLPjF49g.LX761DMqy.Viv5dp6', true), -- usr1password
(2, 'admin1', '$2a$10$j8lPX1FZNfNUa4gtIQvWmON422cFUWRnZ/M35pwK9U.KuZqjwJIkm', true); --adm1password

SELECT setval('users_id_seq', 3, false);

-- Связь пользователей с ролями в таблице USERS_ROLES
INSERT INTO USERS_ROLES (user_id, role_id) VALUES
(1, 1), -- Пользователь User1 связан с ролью ROLE_USER
(2, 2); -- Пользователь Admin1 связан с ролью ROLE_ADMIN
