-- SQL Database set for users where we access the database and then create the table if it doesn't already exist (so that we don't run into any errors)

SHOW DATABASES;

USE asheikh;

CREATE TABLE IF NOT EXISTS users (
            userID INT PRIMARY KEY,
            firstName VARCHAR(255) NOT NULL,
            lastName VARCHAR(255) NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(100) NOT NULL
);
