# Etapa 1: Construcción con Java 21 y Maven
FROM eclipse-temurin:21-jdk AS build

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con Tomcat 10.1.40 (compatible con jakarta.*)
FROM tomcat:10.1.40-jdk21-temurin

# Limpiar las aplicaciones por defecto (opcional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el .war generado en la etapa anterior
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080



