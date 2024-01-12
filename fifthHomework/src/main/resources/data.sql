INSERT INTO AUTHORS (full_name)
SELECT 'Nikolay Gogol'
    WHERE
NOT EXISTS (
SELECT 1 FROM AUTHORS WHERE full_name = 'Nikolay Gogol');

INSERT INTO AUTHORS (full_name)
SELECT 'Fedor Dostoevsky' WHERE
NOT EXISTS (
SELECT 1 FROM AUTHORS WHERE full_name = 'Fedor Dostoevsky');

INSERT INTO GENRES(name)
SELECT 'Novel' WHERE
NOT EXISTS (
SELECT 1 FROM GENRES WHERE name = 'Novel');


INSERT INTO BOOKS(title, author, genre)
SELECT 'Dead souls', 1, 1 WHERE
NOT EXISTS (
SELECT 1 FROM BOOKS WHERE title = 'Dead souls');

INSERT INTO BOOKS(title, author, genre)
SELECT 'Crime and punishment', 2, 1 WHERE
NOT EXISTS (
SELECT 1 FROM BOOKS WHERE title = 'Crime and punishment'
);

