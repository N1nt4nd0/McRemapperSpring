FROM eclipse-temurin:17-jdk
WORKDIR /app
RUN apt-get update && apt-get install -y gradle
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon
COPY build/libs/McRemapper.jar app.jar
CMD ["java", "-jar", "app.jar"]