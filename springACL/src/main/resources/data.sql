INSERT INTO authors (id, full_name) VALUES (1, 'Николай Васильевич Гоголь'), (2, 'Фёдор Михайлович Достоевский') ON CONFLICT (id) DO NOTHING;

INSERT INTO genres (id, name) VALUES (1, 'Роман') ON CONFLICT (id) DO NOTHING;

INSERT INTO books (id, title, genre_id, author_id) VALUES (1, 'Мёртвые души', 1, 1), (2, 'Преступление и наказание', 1, 2) ON CONFLICT (id) DO NOTHING;

INSERT INTO roles (id, role) VALUES (1, 'READER'), (2, 'AUTHOR'), (3, 'ADMIN') ON CONFLICT (Id) DO NOTHING;

INSERT INTO users (id, username, password, role) VALUES (1, 'author', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 2),
                                                        (2, 'admin', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 3),
                                                        (3, 'user', '$2a$12$ISd39dJrp9aT3YqeE0pl9emnL/P.X9Bx8qvvq08MOYjo7ihMKL8Pa', 1)
                                                        ON CONFLICT (id) DO NOTHING;