#!/bin/bash

# Variables for the database connection
db_name="ensolversChallenge"
db_user="root"
db_password="1234"

# Check if MySQL is installed
if command -v mysql >/dev/null 2>&1; then
    echo "MySQL found, setting up the database..."
else
    echo "MySQL not found, please install it first."
    exit 1
fi

# Connect to the MySQL server
mysql -u $db_user -p$db_password << EOF

# Create the database
CREATE DATABASE IF NOT EXISTS $db_name;

# Use the database
USE $db_name;

EOF

echo "Database setup complete."

# Check if Maven is installed
if command -v mvn >/dev/null 2>&1; then
    echo "Maven found, setting up the Spring Boot application..."
else
    echo "Maven not found, please install it first."
    exit 1
fi

# Check if the current directory contains a Spring Boot project
if [ -f pom.xml ]; then
    echo "pom.xml found, installing dependencies..."
    mvn clean install
else
    echo "pom.xml not found, please navigate to a Spring Boot project directory."
    exit 1
fi

# Run the Spring Boot application
mvn spring-boot:run

