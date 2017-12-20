# problemOff-Docker

## Перед стартом проекта необходимо:

* Убедитесь что установлен jdk верисии 8 или выше. Для этого достаточно написать в терминале
> java -version

Если вы видите версию, выше 1.8.0_131 тогда все хорошо

* Убедитесь что установлен maven 3.5.0. 

>mvn -v

Если он не установлен, 
тогда скачиваем последнюю версию с официального сайта https://maven.apache.org/download.cgi

## Настраиваем проект

* Подключем БД: заполняем нужные параметры в src\main\resources\application.properties

* Для изменения порта добавляем в тот же файл server.port=your_port

## Инструкция для старта проекта:
* Склонировать данный репозиторий

> git clone https://github.com/yktaleb/ProblemOff.git

* Build the Project

> mvn clean install

* Run the Project

> java -jar target\problem-off-0.0.1-SNAPSHOT.jar

## Инструкция работы с Docker
* Требования:
Установленный Docker на компьютере

* Run project in Docker

> docker-compose up

* Stop process

> docker-compose stop
