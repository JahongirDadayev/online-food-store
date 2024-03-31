FROM openjdk:17-jdk-slim-buster
EXPOSE 8081
ARG JAR_FILE=target/online-food-store-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]