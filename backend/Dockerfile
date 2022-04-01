#################################################
# Base image for compilation container
#################################################
FROM maven:3.8.4-openjdk-17 as builder

# Choose working directory where we are going to execute commands
WORKDIR /project

# Copy project dependencies
ADD ./pom.xml /project

# Download project dependencies
RUN mvn clean verify --fail-never

# Copy project source
ADD ./src/ /project/src

# Compile project
RUN mvn package -o -DskipTests=true

#################################################
# Base image for aplication container
#################################################
FROM openjdk:17.0.2

# Choose working directory where we can find JAR file
WORKDIR /usr/app/

# Copy JAR from compilation container
COPY --from=builder /project/target/*.jar /usr/app/

# Start expose port from container
EXPOSE 8443

# Execute command
CMD [ "java", "-jar", "wallypop1-0.0.1-SNAPSHOT.jar" ]