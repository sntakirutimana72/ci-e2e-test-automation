FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY .env.test .
COPY src ./src

ENV SELENIUM_HUB_URI=http://selenoid:4444/wd/hub

RUN mvn dependency:go-offline

CMD ["mvn", "test"]
