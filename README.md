# Gateway Service (Gateway_MS-X3)
Микросервис для управления пользователями учетными записями пользователей и их подписками.

![Java](https://img.shields.io/badge/Java-23-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-orange)
![Docker](https://img.shields.io/badge/Docker-✓-lightblue)

## Содержание
- [Особености](#особенности)
- [Технологический стек](#технологический-стек)
- [Требования](#требования)
- [Установка и запуск](#установка-и-запуск)
- [API Endpoints](#api-endpoints)
- [Примеры запросов](#примеры-запросов)
- [Тестирование](#тестирование)
- [Структура проекта](#структура-проекта)

## Особенности
- Маршрутизация запросов по сервисам
- Аунтентификация запросов
- Контейнеризация с Docker
- Интеграция с сервисом KeyCloak
- Обработка внутренних запросов

## Технологический стек
- **Язык**: Java 23
- **Фреймворк**: Spring Boot 3.1
- **Авторизация**: KeyCloak
- **База данных**: PostgreSQL
- **Библиотеки**:
    - Spring Cloud Gateway
    - OAuth2
    - Lombok
- **Инструменты**:
    - Docker
    - Maven

## Требования
- Java 23+
- Maven 3.8+
- Docker 20.10+
- KeyCloak

## Установка и запуск

### 1. Клонирование репозитория
```bash
git clone https://github.com/ender019/User_MS-X3.git
cd Gateway_MS-X3
```
### 2. Запуск с Docker
```bash
docker-compose build --no-cache && docker-compose up -d
```

## API Endpoints

## Примеры запросов
### Регистрация пользователя
```bash
curl -X POST http://localhost:8080/user/add \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123"
    "description": "description"
  }'
```

### Получение данных пользователя
```bash
curl -X GET http://localhost:8080/user/get/?username=name
```
### Получение данных пользователя
```bash
curl -X DELETE http://localhost:8080/user/del/?username=name
```

## Тестирование
### Тестовое покрытие:

- #### Unit-тесты: сервисы, мапперы
- #### Интеграционные тесты: контроллеры, сервисы

### Запуск тестов

```bash
mvn test
```

## Структура проекта
```
src/
└── main/
    ├── java/
    │   ├── com/
    │   │   └── unknown/
    │   │       ├── advices/      # REST контроллеры
    │   │       ├── controllers/  # REST контроллеры
    │   │       ├── models/       # Сущности БД
    │   │       ├── repositories/ # Интерфейсы JPA
    │   │       ├── schemas/      # Data Transfer Objects
    │   │       └── services/     # Бизнес-логика
    │   └── resources/            # Конфиги и миграции
    └── test/                     # Тесты
```
