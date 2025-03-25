# Spring Email Microservice

## About
This is a RESTful API for sending emails using the Email Sender Service, built with Spring Boot and Kotlin!

## Technologies Used
- Kotlin (https://kotlinlang.org/)
- Gradle (https://gradle.org/)
- Spring Boot (https://spring.io/projects/spring-boot)
- Amazon Simple Email Service (https://aws.amazon.com/pt/ses/)

## Prerequisites
Before running this application, make sure you have the following:

1. Java Development Kit (JDK) installed on your machine (Java 17 or higher recommended).
2. Gradle for building and managing the project.
3. AWS SES credentials configured in `application.properties`.

## Setup

1. Clone the repository
```bash
git clone https://github.com/DigaLugas/SpringEmailSender.git
```
2. Navigate to the project directory
```bash
cd SpringEmailSender
```



3. Install dependencies with Gradle
```bash
./gradlew build
```

4. Update the `application.properties` in the `src/main/resources` directory.
```bash
aws.region=us-east-1
aws.accessKeyId=1111111
aws.secretKey=111111
```
## Usage
1. Start application with Gradle
```bash
./gradlew bootRun
```
2. API will be located at `http:localhost:8080`

## Api Endpoints

# Send Email

* Url: `/api/email/send`
* Method : POST
* Request Body:
```bash
{
  "to": "johndude@example.com",
  "subject": "Hello from Email Sender API",
  "body": "This is a test email from the Email Sender API."
}
```
* Response
  Success (HTTP 200 OK):
    ```bash
    "Email sent!"
    ```
  Failure (HTTP 500 Internal Server Error):
     ```bash
    "Failure to send email"
    ```