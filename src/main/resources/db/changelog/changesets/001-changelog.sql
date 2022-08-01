--liquibase formatted sql

--changeset Minaeva Ekaterina:001-changelog.sql
create table Url
(
    id           bigserial primary key,
    short_url    text,
    original_url text
);
--rollback drop table test1;