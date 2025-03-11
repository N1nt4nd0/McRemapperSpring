FROM eclipse-temurin:17-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем Gradle
RUN apt-get update && apt-get install -y gradle

# Копируем весь проект в контейнер
COPY . .

# Даем права на выполнение для gradlew
RUN chmod +x gradlew

# Собираем проект
RUN ./gradlew build --no-daemon

# Проверяем, что файл существует в build/libs и копируем его в контейнер
RUN ls -l build/libs/  # Для отладки, чтобы увидеть, какие файлы есть в папке

# Копируем JAR файл в контейнер
COPY build/libs/McRemapper.jar app.jar

# Запускаем приложение
CMD ["java", "-jar", "app.jar"]
