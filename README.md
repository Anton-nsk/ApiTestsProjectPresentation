#Проект для автотестов на cucumber

##Запуск тестов с формированием отчёта в локальном Allure:
```
mvn clean test -Dplatform=prod && mvn allure:serve
```
## Запуск тестов по тэгам с формированием отчёта в локальном Allure:
Позволяет выборочно запускать тесты.
```
mvn clean test -Dplatform=prod -Dcucumber.options="--tags @tag1,@tag2" && mvn allure:serve
```
Тэги перечисляются через запятую.<br/>

## Добавление нового стенда или изменение адресов для имеющихся

1. В файле *src/resources/dbProperties/db.properties* описаны адреса подключения к базе 
2. Имя адреса коннекта к базе должен иметь маску: ```namestand```
3. При заведении нового стенда нужно добавить адрес подключения к базе 
        
## Полезные ссылки
https://habr.com/ru/post/332754/ - основы по Cucumber


