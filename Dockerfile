FROM openjdk:11
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} /project/getir.jar
ENTRYPOINT ["java","-jar","project/getir.jar"]