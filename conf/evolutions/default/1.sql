# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table t_user (
  id                            varchar(255) not null,
  hash_password                 varchar(255),
  constraint pk_t_user primary key (id)
);


# --- !Downs

drop table if exists t_user;

