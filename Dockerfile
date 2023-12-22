FROM openjdk:17
WORKDIR /app
COPY build/libs/studentslist-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar", "app.jar"]