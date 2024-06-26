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

CREATE TABLE IF NOT EXISTS roles
(
    id SERIAL PRIMARY KEY NOT NULL,
    role VARCHAR NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role INT NOT NULL,
    FOREIGN KEY (id) REFERENCES roles (id)
);
