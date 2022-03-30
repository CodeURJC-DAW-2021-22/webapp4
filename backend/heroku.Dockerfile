#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.8.4-openjdk-17 as builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia las dependencias del proyecto
ADD ./pom.xml /project

# Descarga las dependencias del proyecto
RUN mvn clean verify --fail-never

# Copia el código del proyecto
ADD ./src/ /project/src

# Compila proyecto
RUN mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/$1/web

