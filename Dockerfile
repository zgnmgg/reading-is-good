FROM openjdk:11
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} /project/getir-case-study.jar
ENTRYPOINT ["java","-jar","project/getir-case-study.jar"]