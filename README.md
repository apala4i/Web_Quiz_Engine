# Web_Quiz_Engine

# Задействованные технологии:

- Spring Boot
- Spring JPA
- Hibernate
- Spring Security
- Bean Validation
- H2 Database
- Spring Boot pagination and sorting

# Взаимодействие с сервисом:

GET /quizzes - Получение всех доступных тестов

GET /quizzes/{id} - Получение теста с индексом id

GET /quizzes/completed - Получение всех выполненных пользователем тестов (id теста и дата выполнения)

POST /quizzes - Добавление теста

POST /quizzes/{id}/solve - Решение теста

POST /register - регистрация нового пользователя

DELETE /quizzes/{id} - удаление теста, созданного пользователем

В случае некорректных ситуаций возвращается ответ от сервиса с указанием причины и ошибки.




