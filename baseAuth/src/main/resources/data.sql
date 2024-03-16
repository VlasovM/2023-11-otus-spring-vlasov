INSERT INTO authors (id, full_name) VALUES (1, 'Николай Васильевич Гоголь'), (2, 'Фёдор Михайлович Достоевский') ON CONFLICT (id) DO NOTHING;

INSERT INTO genres (id, name) VALUES (1, 'Роман') ON CONFLICT (id) DO NOTHING;

INSERT INTO books (id, title, genre_id, author_id) VALUES (1, 'Мёртвые души', 1, 1), (2, 'Преступление и наказание', 1, 2) ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, username, password, role) VALUES (1, 'user', '$2a$12$WO1t.iy2F8H6kEXV2ARdLuyrHjqw.EaiqLkRczycg9R5tlmPIqsuO', 'USER'), (2, 'admin', '$2a$12$uEvrDZhniwfNYE4WoDBGLu6gkHSk4U.cj4UtuRrGFqFTHGllo/3zS', 'ADMIN') ON CONFLICT (id) DO NOTHING;