CREATE TABLE IF NOT EXISTS authors
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    text VARCHAR(MAX) NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id)
);