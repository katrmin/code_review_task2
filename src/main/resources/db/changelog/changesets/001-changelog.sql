--liquibase formatted sql

--changeset Minaeva Ekaterina:001-changelog.sql
create table Url
(
    id       bigserial primary key,
    link     text,
    original text,
    count    bigint
);
--rollback drop table Url;