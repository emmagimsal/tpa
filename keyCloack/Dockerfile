FROM openjdk:alpine
ENV TZ=America/Argentina/Mendoza
ENV TERM=xterm

# Add Maintainer Info
LABEL maintainer="rvarela@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081/tcp

ENV VIRTUAL_HOST=stg-keycloack
ENV VIRUTAL_PORT=8081


# The application's jar file
ARG JAR_FILE=target/keycloack-0.0.1-SNAPSHOT-prod.jar

# Add the application's jar to the container
ADD ${JAR_FILE} appppp.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/appppp.jar"]