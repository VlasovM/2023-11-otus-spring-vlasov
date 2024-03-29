CREATE TABLE IF NOT EXISTS AUTHORS
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS GENRES
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS BOOKS
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author BIGINT NOT NULL,
    genre BIGINT NOT NULL,
    FOREIGN KEY (author) REFERENCES AUTHORS (id),
    FOREIGN KEY (genre) REFERENCES GENRES (id)
);
