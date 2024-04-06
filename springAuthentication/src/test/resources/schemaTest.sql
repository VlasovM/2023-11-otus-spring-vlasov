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

CREATE TABLE IF NOT EXISTS roles
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role BIGINT NOT NULL,
    FOREIGN KEY (id) REFERENCES roles (id)
);


