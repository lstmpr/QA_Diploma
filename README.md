## Диплом по курсу "Тестировщик ПО"

[План автоматизации](https://github.com/lstmpr/QA_Diploma/blob/a5e709d4861e6dd1a60ed80ebe588ab90c96b9e4/docs/plan.md)

[Отчет по итогам тестирования](https://github.com/lstmpr/QA_Diploma/blob/19a695379fa31be675482485126eff03d05b69ba/docs/Report.md)

[Отчет по итогам автоматизации](https://github.com/lstmpr/QA_Diploma/blob/447a771e8e71c29d7477f50a2c07b273c81a3019/docs/Summary.md)

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

6. Сформировать отчеты и открыть их в браузере командой:
gradlew allureServe
