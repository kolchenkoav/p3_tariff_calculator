# Проект Тарифный Калькулятор

Расчет стоимости доставки грузов в зависимости от веса
упаковок входящих в состав груза.

## Используемые технологии

- Spring Boot 3
- Maven 3
- JUnit 5
- Swagger

## Локальный запуск

### Требования

Проект использует синтаксис Java 17. Для локального запуска вам потребуется
установленный JDK 17.

### Используя среду разработки IDEA

Откройте проект. Дождитесь индексации. Создайте конфигурацию запуска
или запустите main метод класса [app/src/main/java/ru/fastdelivery/Main.java](app/src/main/java/ru/fastdelivery/Main.java)

### Используя Docker

Вы можете создать Docker образ с приложением и запустить его.

Соберите проект:

```shell
./mvnw clean package
```

Создать образ:

```shell
docker build -t ru.fastdelivery:latest .
```

Запуск контейнера:

```shell
docker run -p 8081:8080 ru.fastdelivery:latest
```

### Используя JAR

Соберите проект:

```shell
./mvnw clean package
```

Запустите Jar файл:

```shell
java -jar app/target/app-1.0-SNAPSHOT.jar
```

## Тестирование кода

### Запуск тестов

Используя встроенный Maven Wrapper запустите
фазу тестов, при этом будет произведена проверка
стиля кода (checkstyle). Отчеты по checkstyle
найдете в файле
[target/site/checkstyle-aggregate.html](target/site/checkstyle-aggregate.html) 

Linux/macOs:

```shell
./mvnw clean test
```

Windows:

```shell
./mvnw.cmd clean test
```

## Swagger

При запущенном приложение вы можете
выполнять запросы используя интерфейс Swagger. 

http://localhost:8080/swagger-ui/index.html

## Структура приложения

Приложения разделено на maven модули, каждый
модуль отвечает за свою область применения:

- [app](app)

Содержит класс запуска приложения, настройки связывания бинов,
чтение значений из файла [application.yml](app/src/main/resources/application.yml)

- [domain](domain)

В модуле находятся все классы участвующие в бизнес логике, такие как Упаковка,
Вес, Валюта, Стоимость. Класс не должен содержать зависимостей на фреймворк Spring Boot и на другие модули проекта.

- [useCase](useCase)

В модуле находится все процессы бизнес логики использующие доменные
классы. Класс не должен содержать зависимостей на фреймворк Spring Boot, только зависит от модуля `domain`

- [web](web)

В модуле находится контроллеры, этот модуль единственный общается с
внешним миром. Содержит зависимости Spring Boot и `domain`, `useCase`.

## Основные изменения
### 1. Расчет стоимости на основе объема
- Добавлена возможность учитывать объем упаковки при расчете тарифа.
- Введен параметр стоимости за кубический метр (`costPerM3`).
- При расчете тарифа учитываются оба параметра: **стоимость по весу и по объему**, и выбирается максимальное значение.

### 2. Использование координат пунктов отправки и получения
- Добавлена поддержка координат отправителя и получателя.
- Для расчета стоимости доставки теперь используется расстояние между координатами.
- Используется формула Хаверсина для вычисления расстояния между точками.
- Округление расстояния вверх с шагом 450 км для корректного расчета тарифа.

## Архитектура проекта
### Основные компоненты:
- `domain` — бизнес-логика и модели данных.
- `useCase` — основные сценарии расчета стоимости доставки.
- `web` — REST API для взаимодействия с клиентами.
- `config` — конфигурационные классы и настройки приложения.
- `util` — вспомогательный класс `GeoDistanceCalculator`.

## Использование API
**POST** `/api/v1/calculate/`

### Пример запроса:
```json
{
  "packages": [
    {"weight": 5000, "length": 100, "width": 50, "height": 30}
  ],
  "currencyCode": "RUB",
  "departure": {"latitude": 55.7558, "longitude": 37.6173},
  "destination": {"latitude": 59.9343, "longitude": 30.3351}
}
```

### Пример ответа:
```json
{
  "totalPrice": 1500.00,
  "minimalPrice": 350.00,
  "currencyCode": "RUB"
}
`

