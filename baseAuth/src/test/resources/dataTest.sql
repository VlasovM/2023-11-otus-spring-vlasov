MERGE INTO authors KEY (id, full_name) VALUES (1, 'Nikolay Gogol'), (2, 'Fedor Dostoevsky');

MERGE INTO genres KEY (id, name) VALUES (1, 'Novel');

MERGE INTO books KEY (id, title, genre_id, author_id) VALUES (1, 'Dead souls', 1, 1), (2, 'Crime and punishment', 2, 1);

MERGE INTO users KEY (id, username, password, role) VALUES (1, 'user', '$2a$12$WO1t.iy2F8H6kEXV2ARdLuyrHjqw.EaiqLkRczycg9R5tlmPIqsuO', 'USER'), (2, 'admin', '$2a$12$uEvrDZhniwfNYE4WoDBGLu6gkHSk4U.cj4UtuRrGFqFTHGllo/3zS', 'ADMIN');