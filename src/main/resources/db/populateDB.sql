DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, date_time, user_id)
VALUES ('ужин1', 100, '2020-12-08 04:55:06', 100000),
       ('ужин2', 200, '2020-11-11 04:30:06', 100000),
       ('ужин3', 300, '2020-10-09 04:05:06', 100000),
       ('ужин4', 400, '2020-09-12 06:05:06', 100000),
       ('ужин5', 500, '2020-08-02 04:11:06', 100000),
       ('ужин6', 600, '2020-04-05 23:45:06', 100000),
       ('ужин7', 700, '2020-03-11 08:30:06', 100000),
       ('ужин8', 800, '2020-01-10 14:04:06', 100000),
       ('ужин9', 900, '2020-01-09 14:05:06', 100000);





