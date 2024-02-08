# Чтение и вывод CSV файла | Read and print information from CSV file
[English version bellow](#English-version)
___
# Навигация
- [Цель](#Цель)
- [Описание](#Описание)
- [Покрытие тестами](#Покрытие-тестами)
- [Локальный запуск](#Локальный-запуск)

---
## Цель:
Создать приложение с помощью Spring IoC, чтобы познакомиться с основной функциональностью IoC, на которой строится весь Spring.
Результат: простое приложение, сконфигурированное XML-контекстом.

## Описание: 
В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов).
Вопросы могут быть с выбором из нескольких вариантов или со свободным ответом - на Ваше желание и усмотрение.
Приложение должна просто вывести вопросы теста из CSV-файла с возможными вариантами ответа (если имеются).

## Покрытие тестами:
Сделал только тест для единственного сервиса, но он не несёт какой-либо функциональности, т.к.:
`(оцениваться будет только попытка написать тест)`

[src -> test -> java -> ru -> javlasov -> firstHomework ](https://github.com/VlasovM/2023-11-otus-spring-vlasov/tree/master/firstHomework/src/test/java/ru/javlasov/firstHomework)

## Локальный запуск
Для того, чтобы запустить проект локально вам необходимы JDK не ниже 17, система контроля версий git, сборщик проектов maven.
Клонировать проект можно через git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

Выполнив команду `mvn clean package` в папке out можно найти jar файл. Запустить jar файл можно через командную строку:

    java -jar <fileNameFromPackageOut.jar>

---

# English version

# Navigation
- [Goal](#Goal)
- [Description](#Description)
- [Test coverage](#Test-coverage)
- [How to local start](#How-to-local-start)

## Goal:
Create an application using Spring IoC to get acquainted with the basic IoC functionality that the whole Spring is built on.
The result: a simple application configured with an XML context.

## Description:
The resources store questions and various answers to them in the form of a CSV file (5 questions).
The questions can be with a choice of several options or with a free answer - at your desire and discretion.
The application should simply output the test questions from a CSV file with possible answer options (if available).

## Test coverage:
Make tests only service, but it's mock test, 'cause:
`(only an attempt to write a test will be evaluated)`

[src -> test -> java -> ru -> javlasov -> firstHomework ](https://github.com/VlasovM/2023-11-otus-spring-vlasov/tree/master/firstHomework/src/test/java/ru/javlasov/firstHomework)

## How to local start
In order to run a project locally, you need a JDK of at least 17, a git version control system, and a maven project builder.
You can clone a project via git bash:

    git clone https://github.com/VlasovM/2023-11-otus-spring-vlasov.git

By executing the command `mvn clean package` you can find a jar file in the out folder. You can run the jar file from the command line:

    java -jar <fileNameFromPackageOut.jar>
