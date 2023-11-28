# Use JDK 17 as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file
COPY ./target/SurveyApplication.jar application.jar

# Expose port 8083
EXPOSE 8083

# Run the jar file
ENTRYPOINT ["java","-jar","application.jar"]
