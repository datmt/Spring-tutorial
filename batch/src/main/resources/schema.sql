-- create table users with first name, last name, birthday, email, password, created_at, updated_at, and id in postgresql
create table users
(
    id         serial primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    birthday   date         not null,
    email      varchar(255) not null,
    password   varchar(255) not null,
    created_at date,
    updated_at date
);