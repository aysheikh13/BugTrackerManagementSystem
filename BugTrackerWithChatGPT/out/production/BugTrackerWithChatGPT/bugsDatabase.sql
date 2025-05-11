-- SQL Database set for bugs where we access the database and then create the table if it doesn't already exist (so that we don't run into any errors)

SHOW DATABASES;

USE asheikh;

CREATE TABLE IF NOT EXISTS bugs (
            bugID VARCHAR(80) PRIMARY KEY,
            bugTitle VARCHAR(155) NOT NULL,
            bugDescription VARCHAR(355) UNIQUE NOT NULL,
            bugPriority INT NOT NULL,
            bugType VARCHAR(100) NOT NULL,
            bugStatus VARCHAR(60) NOT NULL,
            bugDate DATE NOT NULL,
            bugAssignedTo VARCHAR(200) NOT NULL,
            bugReportedTo VARCHAR(200) NOT NULL

);


