# DAO на Spring JDBC | DAO in Spring JDBC
[English version bellow](#English-version)
___
# Навигация
- [Цель](#Цель)
- [Shell команды](#Shell-команды)
- [Покрытие тестами](#Покрытие-тестами)
- [База данных](#База-данных)
- [Локальный запуск](#Локальный-запуск)

---
## Цель:
Переписать приложение для хранения книг на ORM.

Цель: полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка
Результат: Высокоуровневое приложение с JPA-маппингом сущностей

## Shell команды:
- **help** : Список всех доступных команды;
- **aa** : Получить всех авторов;
- **abid N** : Получить автора по id, где N - id автора;
- **ab** : Получить все книги;
- **bbid N** : Получить книгу по id, где N - id книги;
- **bins T A G** : Добавить новую книгу, где T - название книги, A - id автора, G - id жанра; 
- **bupd I T A G** : Обновить книгу, где I - id книги, T - название книги, A - id автора, G - id жанра;
- **bdel I** : Удалить книгу по id, где I - id книги;
- **ag**: Получить все жанры;
- **cbid N**: Получить комментарий по id, где N - id комментария;
- **cbbid N**: Получить комментарий по id книги, где N - id книги;
- **cins T B**: Добавить новый комментарий, где T - текст комментария, B - id книги;
- **cupd I T B**: Обновить комментарий, где I - id комментария, где T - текст комментария, B - id книги;
- **cdel I**: Удалить комментарий по id, где I - id комментария;

## Покрытие тестами:
Тестами покрыты методы репозиториев.

[src -> test -> java -> ru -> javlasov -> sixthhomework -> repositories -> impl](https://github.com/VlasovM/2023-11-otus-spring-vlasov/tree/feature/sixthHomework/src/test/java/ru/javlasov/sixthHomework/repositories/impl)

## База данных
В проекте используется веб-версия базы данных H2. Конфигурация подключения лежит в файле **application.yaml**.  
Инициализация таблиц находится в файле **data.sql**  
Наполнение таблиц находится в файле **schema.sql**

## Локальный запуск
Для того, чтобы запустить проект локально вам необходимы JDK не ниже 17, система контроля версий git, сборщик проектов maven.
Клонировать проект можно через git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

Запустить класс SixthHomeworkApplication и ввести необходимые команды.

---

# English version

# Navigation
- [Goal](#Goal)
- [Description](#Description)
- [Shell commands](#Shell-commands)
- [Test coverage](#Test-coverage)
- [Data base](#Data-base)
- [How to local start](#How-to-local-start)

## Goal:
Rewrite the application for storing books on ORM.

The goal: to fully work with JPA + Hibernate to connect to relational databases through the ORM framework
Result: A high-level application with JPA mapping of entities

## Shell commands:
- **help** : List of all available commands;
- **aa** : Get all the authors;
- **abid N** : Get the author by id, where N is the author's id;
- **ab** : Get all the books;
- **bbid N** : Get a book by id, where N is the book id;
- **bins T A G** : Add a new book, where T is the title of the book, A is the author's id, Genre Id;
- **bupd I T A G** : Update the book, where I is the book id, T is the book title, Author Id, G is the genre id;
- **bdel I** : Delete a book by id, where is the book Id;
- **ag**: Get all genres;
- **cbid N**: Get comment by id, where N - comment id;
- **cbbid N**: Get all comments by book id, where N - book id;
- **cins T B**: Added new comment, where T - comment text, B - book id;
- **cupd I T B**: Update comment, where I - comment id, where T - comment text, B - book id;
- **cdel I**: Delete comment by id, где I - comment id;

## Test coverage
The repository methods are covered by tests.

[src -> test -> java -> ru -> javlasov -> sixthhomework -> repositories -> impl](https://github.com/VlasovM/2023-11-otus-spring-vlasov/tree/feature/sixthHomework/src/test/java/ru/javlasov/sixthHomework/repositories/impl)

## Data base
The project uses the web version of the H2 database. The connection configuration is in the file **application.yaml**.  
The initialization of the tables is in the file **data.sql**  
The contents of the tables are in the file **schema.sql**

## How to local start
In order to run a project locally, you need a JDK of at least 17, a git version control system, and a maven project builder.
You can clone a project through git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

Run the SixthHomeworkApplication class and enter the necessary commands.
