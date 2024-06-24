FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
EXPOSE 8081
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]