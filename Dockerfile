
FROM openjdk:17
ADD target/cv-*.jar cv.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cv.jar"]