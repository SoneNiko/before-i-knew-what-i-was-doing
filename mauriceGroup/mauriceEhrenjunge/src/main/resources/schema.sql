create table if not exists maurice_users (
    username varchar(16) not null primary key,
    password varchar(128) not null,
    enabled boolean not null,
    uuid varchar(36) not null
);

create table if not exists maurice_authorities (
    username varchar(36) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references maurice_users(username)
);

create unique index ix_auth_username on maurice_authorities (username,authority);