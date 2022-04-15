# movies-spring-boot-rest-api


Implement Movie API’s

Design & implement an API for movie in Java 9 or above with below endpoints
1. Creating movies
2. Updating movies
3. Fetching all movies
4. Fetch movies based on year
5. Fetch movies based on ratings

The Movie object will have 3 main attributes - name, year & rating.
Use an in-memory database to store and fetch the movies.
Ensure the application can be run locally and tested.
Ensure there is proper test coverage.

Make use of spring boot/spring webflux , Maven (if possible).

Spring Boot REST API for the movies.

Please follow the steps mentioned below to run the Application in your local:

1. Make sure to install the following softwares mentioned below 

    Java SE Development Kit 8: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html <br/>
    Apache Maven: https://maven.apache.org/download.cgi <br/>
    Intellij IDE: https://www.jetbrains.com/idea/download/#section=windows
    docker: https://docs.docker.com/desktop/windows/install/

2. Clone the repository to your local workspace

3. Import the project to Intellij using the Existing Maven Project Option

4. Build the project using Intellij or below command in cmd

      mvn clean install

5. Create and run Docker image using below command

    i. docker build -t movies/spring-boot-movies-docker .

    ii. docker run -p 8080:8080 movies/spring-boot-movies-docker
      
6. Open swagger api for understanding the apis and testing request/response using below url

    http://localhost:8080/api/swagger-ui.html

7. To run the test cases Right click the project and select Run 'All Tests' or execute below command in cmd

    mvn test

