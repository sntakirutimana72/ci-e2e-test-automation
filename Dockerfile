FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Preload dependencies
RUN mvn dependency:go-offline

CMD ["mvn", "test"]
