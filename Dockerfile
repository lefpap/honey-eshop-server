# Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
COPY target/honey-eshop-server-0.0.1-SNAPSHOT.jar /app/honey-eshop-server.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "honey-eshop-server.jar"]
