## Диплом по курсу "Тестировщик ПО"

## Инструкция по запуску авто-тестов:
1. Склонировать репозиторий
git clone https://github.com/lstmpr/QA_Diploma.git
2. Перейти в папку QA_Diploma
3. Запустить контейнеры:

Для работы с базой данных mysql выполнить команду:
docker-compose -f docker-compose-mysql.yml up -d После прогона тестов остановить контейнеры:
docker-compose -f docker-compose-mysql.yml down

Для работы с базой данных postgres выполнить команду:
docker-compose -f docker-compose-postgres.yml up -d После прогона тестов остановить контейнеры:
docker-compose -f docker-compose-postgres.yml down

4. Запустить приложение:

Для запуска приложения с базой данных mysql выполнить команду:
java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar aqa-shop.jar

Для запуска приложения с базой данных postgres выполнить команду:
java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar aqa-shop.jar

5. Запустить тесты:

Для запуска тестов с базой данных mysql выполнить команду:
gradlew test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app

Для запуска тестов с базой данных postgres выполнить команду:
gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app

6. Сформировать отчеты командой:
gradlew allureReport

7. Открыть отчеты в браузере командой:
gradlew allureServe
