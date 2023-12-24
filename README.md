# Allure Progress Plugin v0.0.4

## Описание

Данный плагин предназначен для отображения состояния прогона автотестов в реальном времени.

## Функционал

+ Визуализация статуса прогона тестов
+ Мониторинг и отчетность в реальном времени
+ Возможность просмотра вложений

## Установка

1. Скачайте наш репозиторий
```
git clone https://github.com/maestro-game/jenkinsplugin
```
2. Установите [Maven](https://maven.apache.org/download.cgi)
3. Соберите проект, используя команду:
```
mvn clean package
```
4. В директории target/ найдите файл формата .hpi и установите его как плагин в настройках Jenkins/Plugins/Advanced, загрузив с ПК
5. Перезапустите Jenkins.
6. Плагин установлен.

## Использование

После установки плагина в окне прогона автотестов будет отображаться количество пройденных тестов по статусам. 

## Полезные ссылки

+ [Official Jenkis Plugin Guide](https://www.jenkins.io/doc/developer/tutorial/)
+ [Apache Maven Install Guide](https://maven.apache.org/install.html)
+ [Allure Plugin Repository](https://github.com/jenkinsci/allure-plugin)

## Авторы
+ [Белов Вадим](https://github.com/maestro-game)
+ [Трушин Никита](https://github.com/truhinnm)
+ [Нургалеев Мансур](https://github.com/Mansur908)
+ [Галиев Булат](https://github.com/GalievBulat)

