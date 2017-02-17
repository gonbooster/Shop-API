# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  constraint pk_category primary key (id)
);

create table password (
  id                            bigint auto_increment not null,
  password_hash                 varchar(255),
  up_date                       datetime(6) not null,
  constraint pk_password primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  amount                        integer,
  price                         double,
  user_id                       bigint,
  constraint pk_product primary key (id)
);

create table review (
  id                            bigint auto_increment not null,
  stars                         double,
  description                   varchar(255),
  user_id                       bigint,
  constraint pk_review primary key (id)
);

create table sub_category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  category_id                   bigint,
  constraint pk_sub_category primary key (id)
);

create table sub_category_product (
  sub_category_id               bigint not null,
  product_id                    bigint not null,
  constraint pk_sub_category_product primary key (sub_category_id,product_id)
);

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  password_id                   bigint,
  constraint uq_user_password_id unique (password_id),
  constraint pk_user primary key (id)
);

alter table product add constraint fk_product_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_product_user_id on product (user_id);

alter table review add constraint fk_review_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_review_user_id on review (user_id);

alter table sub_category add constraint fk_sub_category_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_sub_category_category_id on sub_category (category_id);

alter table sub_category_product add constraint fk_sub_category_product_sub_category foreign key (sub_category_id) references sub_category (id) on delete restrict on update restrict;
create index ix_sub_category_product_sub_category on sub_category_product (sub_category_id);

alter table sub_category_product add constraint fk_sub_category_product_product foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_sub_category_product_product on sub_category_product (product_id);

alter table user add constraint fk_user_password_id foreign key (password_id) references password (id) on delete restrict on update restrict;


# --- !Downs

alter table product drop foreign key fk_product_user_id;
drop index ix_product_user_id on product;

alter table review drop foreign key fk_review_user_id;
drop index ix_review_user_id on review;

alter table sub_category drop foreign key fk_sub_category_category_id;
drop index ix_sub_category_category_id on sub_category;

alter table sub_category_product drop foreign key fk_sub_category_product_sub_category;
drop index ix_sub_category_product_sub_category on sub_category_product;

alter table sub_category_product drop foreign key fk_sub_category_product_product;
drop index ix_sub_category_product_product on sub_category_product;

alter table user drop foreign key fk_user_password_id;

drop table if exists category;

drop table if exists password;

drop table if exists product;

drop table if exists review;

drop table if exists sub_category;

drop table if exists sub_category_product;

drop table if exists user;

