MERGE INTO authors KEY (id, full_name) VALUES (1, 'Nikolay Gogol'), (2, 'Fedor Dostoevsky');

MERGE INTO genres KEY (id, name) VALUES (1, 'Novel');

MERGE INTO books KEY (id, title, genre_id, author_id) VALUES (1, 'Dead souls', 1, 1), (2, 'Crime and punishment', 2, 1);

MERGE INTO roles KEY (id, role) VALUES (1, 'READER'), (2, 'AUTHOR'), (3, 'ADMIN');

MERGE INTO users KEY (id, username, password, role) VALUES (1, 'author', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 2),
                                                           (2, 'admin', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 3),
                                                           (3, 'user', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 1);