# Etapa 1: Construir el WAR usando Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia pom.xml y descarga dependencias primero (para hacer caché)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia el resto del código y compila
COPY . .
RUN mvn clean package

# Etapa 2: Copiar el WAR al contenedor final de Tomcat
FROM tomcat:9.0-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
