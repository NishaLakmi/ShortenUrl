# ShortenUrl
URL Shorten Project
The URL shortener operates by receiving an original URL through a POST request, checking it against a blacklist to ensure it doesn't propagate harmful content. If cleared, the URL is transformed using the MD5 hashing algorithm. The shortened URL and its original counterpart are stored in a PostgreSQL database. A GET request with the shortened URL fetches the original URL from the database and redirects the user. The project leverages Google Cloud Run for its serverless infrastructure, providing scalability and ease of deployment.

Dependencies
Java
Spring Boot
PostgreSQL
Docker
Google Cloud SDK
GitHub
Cloud Build API enabled on Google Cloud Console
Google Cloud Run API enabled
Google Cloud SQL API enabled

Installing
1. Set Up  Google Cloud Project
   Creat a new project in the Google Cloud Console
   Enable Cloud Run, Cloud Build and Cloud SQL APIs
   
2. Configure Databse
   Set up a PostgresSQL instance in Google Cloud SQl
   Note the connection details(instance name, database name, username , password)

3. Prepare Application
   Create a 'Dockerfile' to containerize the application
   FROM maven:3.9.6 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.11_9-jre-focal
COPY --from=builder /app/target/Url-Shortener-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

4. Building and Push Docker Image
docker build -t url-shortener .
docker push gcr.io/shortenurl-project-421309/url-shortener:shorturl

5. Deploy to cloud Run
   gcloud run deploy url-shortener --image gcr.io/your-project-id/url-shortener --platform managed


Executing Program
1. Run Locally
   ./ mnvw spring-boot🇧🇳

Configuration Cloud Build and Trigers

1. Connect GitHub Repository:
   In Google Cloud Console, Connect your GitHub repository.
   Set up build triggers to automate Docker image and Deployments.
2. Set Up Build Configuration
   Create 'cloudbuild.yaml' file

Connecting to Cloud SQL
1. Set Up Cloud SQL
   Create and Configure a PostGresSQL instance.
   Enable public IP and Configure authorized networks for access.
2. Secure Connections:
   Use Cloud SQL Auth proxy or configure SSL connection.
3. Connect Form Cloud Run:
   Set up enviornment variable in Cloud Run Service for database connection details.

Help
For Common issues or problems, refer to the following:
1. Check Logs
2. Database Connection Errors.
   Verify that Cloud Run service has the necessary permissions to access Cloud SQL.
   Ensure that the connection details are correct and that the database instance is accessible.
4. Building and Deployment Issues
   Check cloudbuild.yaml for errors.
   Ensure Docker and Cloud Build configurations are correct.




