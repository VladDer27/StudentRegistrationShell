FROM openjdk:17-oracle

WORKDIR /app

COPY out/artifacts/demo_jar/demo.jar app.jar

ENV CREATE_STUDENTS_ON_START=true

CMD ["java", "-jar", "app.jar"]