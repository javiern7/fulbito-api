create table users (
                       id bigint primary key auto_increment,
                       email varchar(150) not null unique,
                       password_hash varchar(255) not null,
                       role varchar(30) not null, -- ADMIN | USER
                       active tinyint(1) not null default 1,
                       created_at timestamp not null default current_timestamp
);

create table leagues (
                         id bigint primary key auto_increment,
                         name varchar(120) not null,
                         city varchar(120),
                         active tinyint(1) not null default 1,
                         created_at timestamp not null default current_timestamp,
                         updated_at timestamp null
);

create index idx_leagues_active on leagues(active);
