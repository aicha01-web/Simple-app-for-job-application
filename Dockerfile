
FROM openjdk:17
ADD target/cv-0.0.1-SNAPSHOT.jar cv.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pointage.jar"]