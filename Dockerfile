# Imagen base de Tomcat + Java 17
FROM tomcat:9.0-jdk17-temurin

# Elimina las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia el archivo .war generado al contenedor
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto 8080 (Render detecta esto automáticamente)
EXPOSE 8080
