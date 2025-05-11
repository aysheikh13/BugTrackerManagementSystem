# BugTrackerManagementSystem

************INSTRUCTIONS************

1. Firstly, ensure you are using IntelliJ IDEA as it best suits the project structure and guarantees its working functionalities. Also, make sure that the version of Java (basically the Java SDK) being used is Amazon Corretto 22 (Java 22). I ran into errors with certain libraries because I was using Java 52, so ensure you change that by going to Project Structure -> Project -> SDK and change it through downloading Amazon Corretto 22.

2. Download JavaFX SDK via gluon or any other website that offers the SDK. Make sure to download the latest version, ensure your downloading the SDK (not the jmods), use the x64 architecture, and make sure to look for the SDK that suits your OS (whether its Mac or Windows). Next, go to Project Structure -> Libraries and click the plus sign, then click Java, then click the folder titled lib inside your JavaFX-SDK folder and click apply. Also, go to Run -> Edit Configurations -> VM Options (Right below the JDK) and input the following command:

```
--module-path "@FilePathForJavaFX-SDK\lib" --add-modules javafx.controls,javafx.fxml.
```
Make Sure to get the file path to the JavaFX lib folder that is found inside the zip folder for the JavaFX SDK you downloaded.  

4. Download SQL Drivers for the database you will use. My code in particular uses MariaDB SQL drivers to ensure that there is a proper connection and the JDBC works correctly. However, the cool aspect of my project is that if you have a DBMS of your own you can substitute the details (name, password, port number, etc) especially if its some variation of SQL or you are using a Oracle Database. Similar to the last step, go to Project Structure -> Libraries and click the plus sign, then click Java, then click the folder where your driver files (the .jar files) are located. Click apply and you should be set.

5. Insert your database details in the Data Sources and Drivers which you can do by clicking the Database button on the right hand side of IntelliJ and then clicking the plus buton. Here, you can add the details of your database like username, password, and port number as well as providing an SSH tunnel that may be required if you want to access a server that has a database already (like Fordham's Storm Server). This is mainly for running the SQL scripts I have, but its also good to see if your database is being accessed appropriately and data is being changed properly (IntelliJ provides a great visual table of your schema/database as well as the tables themselves are easier to read).

(Optional)
6. Lastly, to fully finish and run this code perform an SSH Tunnel to a server that contains the database. This step is optional if you have a database of your own and do not require a SSH Tunnel to access the database. But for running my code this is what I used to run it and ensure that the JDBC can properly establish connection and store, insert, delete, and update the data.

```
ssh -L localAddress:remoteAddress serverAddress
```
