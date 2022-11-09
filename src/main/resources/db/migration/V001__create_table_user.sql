create table if not exists users
(
    id_user                      uuid not null
        primary key
        unique,
    user_name               varchar(255) default NULL,
    full_name               varchar(255) default NULL,
    password                varchar(255) default NULL,
    account_non_expired     bit(1)       default NULL,
    credentials_non_expired bit(1)       default NULL,
    enabled                 bit(1)       default NULL
);

