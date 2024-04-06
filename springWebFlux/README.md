# WebFlux

[English version bellow](#English-version)
___

# Навигация

- [Цель](#Цель)
- [Возможности приложения](#Возможности-приложения)
- [Покрытие тестами](#Покрытие-тестами)
- [База данных](#База-данных)
- [Локальный запуск](#Локальный-запуск)

## Цель:

Цель: разрабатывать Responsive и Resilent приложения на реактивном стеке Spring c помощью Spring Web Flux и Reactive Spring Data Repositories
Результат: приложение на реактивном стеке Spring

## Возможности приложения:

Перейдя на главную страницу, можно получить список книг, которые лежат в библиотеке.
Книгу можно изменить, удалить или добавить новую.
Т.е. существуют CRUD операции только для сущности Book.
Без авторизации можно зайти на стартовую страницу. На остальные - только авторизированным пользователям.


## Покрытие тестами:

Тестами покрыты все репозитории и контроллер.

## База данных:

В проекте используется база Mongo DB.
Инициализация данных происходит через конфигурационный класс DatabaseChangelog

## Локальный запуск

Для того, чтобы запустить проект локально вам необходимы JDK не ниже 17, система контроля версий git, сборщик проектов
maven. Клонировать проект можно через git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

Запустить класс SpringMvc и перейдите на локальный хост: http://localhost:8080

---

# English version

# Navigation

- [Goal](#Goal)
- [Application Features](#Features-applications)
- [Test coverage](#Coverage by tests)
- [Database](#Database)
- [Local Launch](#Local-launch)

## Goal:

Goal: to develop Responsive and Resident applications on the Spring reactive stack using Spring Web Flow and Reactive Spring Data Repositories
Result: an application on the Spring reactive stack

## Features application:

By going to the main page, you can get a list of books that are in the library.
The book can be changed, deleted, or a new one can be added.
That is, there are CRUD operations only for the Book entity.
Without authorization, you can go to the start page. For the rest, only for authorized users.

## Test coverage

All repositories and the controller are covered with tests.

## Data base

The project uses the MongoDB database.
Data initialization takes place through the DatabaseChangelog configuration class

## How to local start

In order to run a project locally, you need a JDK of at least 17, a git version control system, and a maven project
builder. You can clone a project through git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

Run the Spring Mvc class and navigate to the local host: http://localhost:8080
