INSERT INTO authors (id, full_name) VALUES (1, 'Николай Васильевич Гоголь'), (2, 'Фёдор Михайлович Достоевский') ON CONFLICT (id) DO NOTHING;

INSERT INTO genres (id, name) VALUES (1, 'Роман') ON CONFLICT (id) DO NOTHING;

INSERT INTO books (id, title, genre_id, author_id) VALUES (1, 'Мёртвые души', 1, 1), (2, 'Преступление и наказание', 1, 2) ON CONFLICT (id) DO NOTHING;