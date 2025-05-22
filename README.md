# PaymentService

A Spring Boot application for processing payments using multiple payment methods.

## Features

- Supports multiple payment methods (Credit Card, Bank Transfer, PayPal)
- Strategy pattern for payment processing
- Payment details initialization at startup
- RESTful service architecture

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- A running database for this was MySQL selected
- Docker (optional, for running MySQL)

#[##]() Build and Run

```bash
mvn clean install
mvn spring-boot:run

docker run -d \
    --name mi-mysql \
    -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
    -e MYSQL_DATABASE=$MYSQL_DATABASE \
    -e MYSQL_USER=$MYSQL_USER \
    -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
    -p 3306:3306 \
    mysql:latest
    
#Script to create the database
```sql
CREATE DATABASE `BDGlobant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
-- BDGlobant example for strategy pattern

To populate the database with some data, you can use the following SQL scripts in folder DatabaseScripts:
- CreditCard.sql
- PaymentDetails.sql


# StrategyPattern
<<<<<<< HEAD
=======
# StrategyPattern
>>>>>>> 08c9744 (first commit)
# StrategyPattern
# StrategyPattern
# StrategyPattern
