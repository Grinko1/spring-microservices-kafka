# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл в контейнер
COPY target/payment-0.0.1-SNAPSHOT.jar /app/payment.jar

# Открываем порт для микросервиса
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "payment.jar"]
