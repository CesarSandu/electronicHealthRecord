FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:9.0
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]
