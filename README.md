# 📦 FastDelivery - Калькулятор тарифов доставки  

## 📌 Описание  
FastDelivery — это сервис расчета стоимости доставки, разработанный на Java с использованием Spring Boot. Проект позволяет рассчитать стоимость перевозки грузов на основе веса, объема и расстояния между пунктами отправления и назначения.

## 🚀 Функциональность  
✔️ Расчет стоимости доставки с учетом веса, объема и расстояния  
✔️ Валидация входных данных  
✔️ Поддержка различных валют  
✔️ API-документация с OpenAPI (Swagger)  
✔️ Юнит- и интеграционные тесты  

## 🏗️ Архитектура  
Проект построен по многомодульной архитектуре:  
- `domain` – базовые сущности и бизнес-логика  
- `useCase` – сервисы расчета тарифов  
- `app` – точка входа в приложение  
- `web` – API-контроллеры и обработка запросов  

## 🛠️ Технологии  
- Java 17  
- Spring Boot 3  
- PostgreSQL  
- Testcontainers  
- JUnit 5, Mockito, AssertJ  
- Lombok  
- OpenAPI (Swagger)  

## 🔧 Запуск проекта  

### 🏗 Требования  
- Java 17+  
- Maven 3+  
- PostgreSQL  

### ⏳ Установка и запуск  
```bash
# Клонировать репозиторий
git clone https://github.com/your-repo/FastDelivery.git
cd FastDelivery

# Собрать проект
mvn clean install

# Запустить приложение
mvn spring-boot:run
```

## 📡 API Документация  
После запуска API будет доступно по адресу:  
📜 [Swagger UI](http://localhost:8080/swagger-ui.html)  

## 🧪 Тестирование  
Запустить все тесты:  
```bash
mvn test
```

## 📌 Контакты  
📧 Email: support@fastdelivery.com  
🐙 GitHub: [FastDelivery](https://github.com/your-repo/FastDelivery)  

## 📜 Лицензия  
Этот проект лицензирован под MIT License.
