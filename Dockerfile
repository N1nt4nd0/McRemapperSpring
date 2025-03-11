FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . ./
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon
CMD ["java", "-jar", "/app/build/libs/McRemapper.jar"]
