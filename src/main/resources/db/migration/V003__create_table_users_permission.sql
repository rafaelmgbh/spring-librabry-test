create table if not exists users_permission
(
    id_user uuid not null,
    id_permission bigint not null,
    PRIMARY KEY (id_user, id_permission),
    FOREIGN KEY (id_user) REFERENCES users(id_user),
    FOREIGN KEY (id_permission) REFERENCES permission(id_permission)


);

