## Диплом по курсу "Тестировщик ПО"

[План автоматизации](https://github.com/lstmpr/QA_Diploma/blob/a5e709d4861e6dd1a60ed80ebe588ab90c96b9e4/docs/plan.md)

[Отчет по итогам тестирования](https://github.com/lstmpr/QA_Diploma/blob/2b43115d49fb7f7e83d2d71c1add9123133ec2d2/docs/Report.md)

[Отчет по итогам автоматизации](https://github.com/lstmpr/QA_Diploma/blob/2b43115d49fb7f7e83d2d71c1add9123133ec2d2/docs/Summary.md)

---------------------

## Инструкция по запуску авто-тестов:

1. Склонировать репозиторий
git clone https://github.com/lstmpr/QA_Diploma.git
2. Перейти в папку QA_Diploma
3. Запустить контейнеры Node.js, MySQL, PostgreSQL выполнив команду docker-compose up --build.

4. Запустить приложение:

* для базы данных MySQL выполнив команду: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar

* для базы данных PostgreSQL выполнив команду: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar


5. Запустить тесты выполнив команду:

* для базы данных MySQL выполнив команду: gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app";

* для базы данных PostgreSQL выполнив команду: gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app".

6. Сформировать отчеты командой:
gradlew allureReport

7. Открыть отчеты в браузере командой:
gradlew allureServe
