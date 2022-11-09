insert into author (id, name, picture)
values ('d00d4e42-66b5-4499-9fa6-ee23b8987d61', 'Rafael Drumond ', 'john_doe.jpg'),
       ('b0857dbe-ae00-4de2-9af7-0abdd2cdcb4d', 'Davi De Andrade', 'jane_doe.jpg');

INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title)
VALUES ('8966da31-fd3b-438a-a48a-afecfee580d7', 'b0857dbe-ae00-4de2-9af7-0abdd2cdcb4d', 'Tecnologia',
        'There are many variations of passages of Lorem Ips', 'Lorem', 'POO 1.0');
INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title)
VALUES ('1d031e4f-cb6c-435a-8268-18b41f0fd4f7', 'd00d4e42-66b5-4499-9fa6-ee23b8987d61', 'Esportes',
        'It is a long established fact that a reader will b', 'Lorem', 'Copa do Mundo 2022');
INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title)
VALUES ('43493d30-2a23-4965-a828-3bae9f0e8667', 'b0857dbe-ae00-4de2-9af7-0abdd2cdcb4d', 'Tecnologia',
        'Contrary to popular belief, Lorem Ipsum is not sim', 'Lorem', 'Algoritmos e Estrutura de dados');



INSERT INTO permission (description, role)
VALUES ('User Admin', 'ROLE_ADMIN'),
       ('Common user', 'ROLE_USER');


INSERT INTO public.users (id_user, user_name, full_name, password, account_non_expired, credentials_non_expired, enabled,
                          account_non_locked, email)
VALUES ('deeaf972-29c7-40eb-93f8-6a44968db70a', 'rafael', 'Rafael Santos',
        'e7f229211355c2d013e156613225fad80992967389d12264f870eecc8c5a7b8f3212aa9d9be8e17f', '1', '1', '1', '1', 'rafael@gmail.com');
INSERT INTO public.users (id_user, user_name, full_name, password, account_non_expired, credentials_non_expired, enabled,
                          account_non_locked, email)
VALUES ('b72e0718-187d-4f0b-aa96-e7ec0df58435', 'davi', 'Davi Santos',
        'e7f229211355c2d013e156613225fad80992967389d12264f870eecc8c5a7b8f3212aa9d9be8e17f', '1', '1', '1', '1', 'davi@gmail.com');

INSERT INTO users_permission (id_user, id_permission)
VALUES ('deeaf972-29c7-40eb-93f8-6a44968db70a', 1);

INSERT INTO users_permission (id_user, id_permission)
VALUES ('b72e0718-187d-4f0b-aa96-e7ec0df58435', 2);