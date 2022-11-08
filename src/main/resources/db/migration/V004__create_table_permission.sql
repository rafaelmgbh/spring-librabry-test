create table if not exists permission
(
    id bigserial   primary key,
    description varchar(255) default null
);

