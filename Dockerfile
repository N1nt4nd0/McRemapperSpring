FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/McRemapper.jar app.jar
CMD ["java", "-jar", "app.jar"]
