SELECT
    'CREATE DATABASE IF NOT EXISTS library
    WITH
    OWNER = postgres
    ENCODING = ''UTF8''
    LC_COLLATE = ''Russian_Russia.1251''
    LC_CTYPE = ''Russian_Russia.1251''
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'library');

CREATE TABLE IF NOT EXISTS authors
(
    id SERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books
(
    id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id SERIAL PRIMARY KEY NOT NULL,
    text VARCHAR NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id)
);
