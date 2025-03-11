FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . ./
CMD ["java", "-jar", "/app/build/libs/McRemapper.jar"]
