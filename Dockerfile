FROM eclipse-temurin:21-jre

COPY *.jar /app/user-role-service.jar

WORKDIR /app

CMD ["java", "-jar", "user-role-service.jar"]