--liquibase formatted sql

--changeset Minaeva Ekaterina:001-changelog.sql
create table Url
(
    id       bigserial primary key,
    link     text unique,
    original text unique,
    count    bigint default 0
);
--rollback drop table Url;