# Указываем начальный сборщик с которым будет работать линукс
FROM openjdk:8-jdk-alpine
# Указываем имя нашего приложения в виде параметра, для сборки нужен некий параметр, который назвается jar_file
ARG JAR_FILE
# Создаем в файловой система папку в которой будем размещать приложение makedir создаем app
RUN mkdir -p /apps
#Копируем файл собранный в результате нашей сборки приложения в внутрь контейнера target и задаем имя например app.jar
COPY ./target/${JAR_FILE} /apps/app.jar
# Создаем entrypoint_sh скрипт
# Далее копируем entrypoint_sh
COPY ./entrypoint.sh /apps/entrypoint.sh
# Вызываем команду changemode
RUN chmod +x /apps/entrypoint.sh
# Запускаем. При создании образа в него будут добавлены настройки и файлы выше.
CMD["/apps/entrypoint.sh"]