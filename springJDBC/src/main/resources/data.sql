MERGE INTO AUTHORS KEY (id, full_name) VALUES (1, 'Nikolay Gogol'), (2, 'Fedor Dostoevsky');

MERGE INTO GENRES KEY (id, name) VALUES (1, 'Novel');

MERGE INTO BOOKS KEY (id, title) VALUES (1, 'Dead souls', 1, 1), (2, 'Crime and punishment', 2, 1);

