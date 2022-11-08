insert into author (id, name, picture)
values ('d00d4e42-66b5-4499-9fa6-ee23b8987d61', 'Rafael Drumond ', 'john_doe.jpg') ,
       ('b0857dbe-ae00-4de2-9af7-0abdd2cdcb4d', 'Davi De Andrade', 'jane_doe.jpg');

INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title) VALUES ('8966da31-fd3b-438a-a48a-afecfee580d7', '06cbb78b-2141-4064-b262-43877b84134d', 'Tecnologia', 'There are many variations of passages of Lorem Ips', 'Lorem', 'POO 1.0');
INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title) VALUES ('1d031e4f-cb6c-435a-8268-18b41f0fd4f7', 'd00d4e42-66b5-4499-9fa6-ee23b8987d61', 'Esportes', 'It is a long established fact that a reader will b', 'Lorem', 'Copa do Mundo 2022');
INSERT INTO papers (id, author_id, category, first_paragraph, sumary, title) VALUES ('43493d30-2a23-4965-a828-3bae9f0e8667', '06cbb78b-2141-4064-b262-43877b84134d', 'Tecnologia', 'Contrary to popular belief, Lorem Ipsum is not sim', 'Lorem', 'Algoritmos e Estrutura de dados');


INSERT INTO users (id,user_name, full_name, password, account_non_expired, account_non_locked,credentials_non_expired, enabled) VALUES
                                                                                                                                    ('deeaf972-29c7-40eb-93f8-6a44968db70a','rafael', 'Rafael Santos', '19bbf735b27066f2f145e602624e1b24a3fbc54cd5dfd3143fc5feea6bdee9e139ca7332d4806b9f', b'1', b'1', b'1', b'1'),
                                                                                                                                    ('b72e0718-187d-4f0b-aa96-e7ec0df58435','davi', 'Davi Santos', '19bbf735b27066f2f145e602624e1b24a3fbc54cd5dfd3143fc5feea6bdee9e139ca7332d4806b9f', b'1', b'1', b'1', b'1');

INSERT INTO permission (description) VALUES
                                         ('ADMIN'),
                                         ('MANAGER'),
                                         ('COMMON_USER');

INSERT INTO public.users (id, user_name, full_name, password, account_non_expired, credentials_non_expired, enabled, account_non_locked) VALUES ('deeaf972-29c7-40eb-93f8-6a44968db70a', 'rafael', 'Rafael Santos', '19bbf735b27066f2f145e602624e1b24a3fbc54cd5dfd3143fc5feea6bdee9e139ca7332d4806b9f', '1', '1', '1', '1');
INSERT INTO public.users (id, user_name, full_name, password, account_non_expired, credentials_non_expired, enabled, account_non_locked) VALUES ('b72e0718-187d-4f0b-aa96-e7ec0df58435', 'davi', 'Davi Santos', '19bbf735b27066f2f145e602624e1b24a3fbc54cd5dfd3143fc5feea6bdee9e139ca7332d4806b9f', '1', '1', '1', '1');

INSERT INTO user_permission (id_user, id_permission) VALUES ('deeaf972-29c7-40eb-93f8-6a44968db70a', 1);
INSERT INTO user_permission (id_user, id_permission) VALUES ('deeaf972-29c7-40eb-93f8-6a44968db70a', 2);
INSERT INTO user_permission (id_user, id_permission) VALUES ('b72e0718-187d-4f0b-aa96-e7ec0df58435', 1);