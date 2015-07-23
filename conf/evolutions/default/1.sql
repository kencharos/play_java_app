# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table T_USER (
  id                        varchar(255) not null,
  hash_password             varchar(255),
  constraint pk_T_USER primary key (id))
;

create sequence T_USER_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists T_USER;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists T_USER_seq;

