insert into books (attachment_id, description, title, user_id)
VALUES (1, 'Some full Description', 'Атом одатлар', 1);

insert into books (attachment_id, description, title, user_id)
VALUES (1, 'Some full Description', 'Сармоядор', 1);

insert into books (attachment_id, description, title, user_id)
VALUES (1, 'Some full Description', 'Бепарволикнинг нозик санъати', 1);

insert into author (full_name) values ('Жеймс Клир');
insert into author (full_name) values ('Теодор Драйзер');
insert into author (full_name) values ('Марк Мэнсон');

insert into books_authors (books_id, authors_id) VALUES (1, 1);
insert into books_authors (books_id, authors_id) VALUES (2, 2);
insert into books_authors (books_id, authors_id) VALUES (3, 3);

insert into book_collection (user_id, book_id) VALUES (1, 1);
insert into book_collection (user_id, book_id) VALUES (1, 2);