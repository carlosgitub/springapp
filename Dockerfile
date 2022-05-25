FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/springapp-0.0.1-SNAPSHOT.jar"]