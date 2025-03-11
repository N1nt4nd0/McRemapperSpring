FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/McRemapper.jar app.jar

CMD ["java", "-jar", "app.jar"]
