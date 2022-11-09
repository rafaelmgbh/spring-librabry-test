create table if not exists permission
(
    id_permission bigserial   primary key,
    description varchar(255) default null
);

