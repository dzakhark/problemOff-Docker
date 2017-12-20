FROM maven:3.5.2-jdk-8

COPY . /app

RUN cd /app && mvn clean install -DskipTests

EXPOSE 8080

ENV SPRING_DATASOURCE_URL jdbc:postgresql://postgres:5432/postgres
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres

CMD ["java", "-jar", "/app/target/problem-off-0.0.1-SNAPSHOT.jar"]
