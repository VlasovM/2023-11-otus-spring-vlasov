INSERT INTO authors (id, full_name) VALUES (1, 'Nikolay Gogol'), (2, 'Fedor Dostoevsky') ON CONFLICT (id) DO NOTHING;

INSERT INTO genres (id, name) VALUES (1, 'Novel') ON CONFLICT (id) DO NOTHING;

INSERT INTO books (id, title, genre_id, author_id) VALUES (1, 'Dead souls', 1, 1), (2, 'Crime and punishment', 1, 2) ON CONFLICT (id) DO NOTHING;

INSERT INTO comments (id, text, book_id) VALUES (1, 'This book is cool', 1), (2, 'Wow! This awesome!', 1) ON CONFLICT (id) DO NOTHING;