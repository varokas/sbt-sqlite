--liquibase formatted sql

--changeset default:1
create table person (
  id integer not null primary key autoincrement,
  firstname varchar(80),
  lastname varchar(80) not null,
  state varchar(2)
);
