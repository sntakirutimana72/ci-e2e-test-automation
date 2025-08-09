FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY .env.test .
COPY src ./src

# Copy and set entrypoint
COPY matrix-runner.sh .
RUN chmod +x /app/matrix-runner.sh

ENV SELENIUM_HUB_URI=http://selenoid:4444/wd/hub

# Preload dependencies
RUN mvn dependency:go-offline

ENTRYPOINT ["/app/matrix-runner.sh"]
