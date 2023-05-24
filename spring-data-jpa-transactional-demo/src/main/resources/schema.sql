drop table if exists account;
drop table if exists car;
drop table if exists maker;

create table user
(
    id   serial primary key,
    name varchar(255) unique not null
);
create table account
(
    id      serial primary key,
    user_id int                 not null,
    balance int                 not null default 0,
    name    varchar(255) unique not null
);

-- Create foreign key
alter table account
    add constraint fk_account_user foreign key (user_id) references user (id);


-- Cars and maker
create table car
(
    id   serial primary key,
    name varchar(255) unique not null
);

create table maker
(
    id   serial primary key,
    name varchar(255) unique not null
);