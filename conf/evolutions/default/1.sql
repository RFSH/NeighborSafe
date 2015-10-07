# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table neighbors (
  username                  varchar(255) not null,
  password                  varchar(255),
  constraint pk_neighbors primary key (username))
;

create table neighborprofiles (
  profile_id                varchar(255) not null,
  home_number               varchar(255),
  zip_code                  varchar(255),
  address                   varchar(255),
  medical_cond              varchar(255),
  utility_gaz               varchar(255),
  utility_electric          varchar(255),
  utility_water             varchar(255),
  cpr                       boolean,
  first_aid                 boolean,
  other_med_trainings       varchar(255),
  have_generator            boolean,
  have_food                 boolean,
  other_med_supplies        varchar(255),
  languages                 varchar(255),
  other                     varchar(255),
  constraint pk_neighborprofiles primary key (profile_id))
;

create table people (
  id                        varchar(255) not null,
  profile_profile_id        varchar(255),
  is_adult                  boolean,
  age                       integer,
  name                      varchar(255),
  email                     varchar(255),
  cell_number               varchar(255),
  constraint pk_people primary key (id))
;

create sequence neighbors_seq;

create sequence neighborprofiles_seq;

create sequence people_seq;

alter table neighborprofiles add constraint fk_neighborprofiles_neighbor_1 foreign key (profile_id) references neighbors (username) on delete restrict on update restrict;
create index ix_neighborprofiles_neighbor_1 on neighborprofiles (profile_id);
alter table people add constraint fk_people_profile_2 foreign key (profile_profile_id) references neighborprofiles (profile_id) on delete restrict on update restrict;
create index ix_people_profile_2 on people (profile_profile_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists neighbors;

drop table if exists neighborprofiles;

drop table if exists people;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists neighbors_seq;

drop sequence if exists neighborprofiles_seq;

drop sequence if exists people_seq;

