#################################################
# Base image for compilation container
#################################################
FROM maven:3.6.3-openjdk-17 as builder

# Choose working directory where we are going to execute commands
WORKDIR /project

# Copia las dependencias del proyecto
ADD ./pom.xml /project

# Download project dependencies
RUN mvn clean verify --fail-never

# Copy project source
ADD ./src/ /project/src

# Build image
RUN mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/daw-webapp4/web

