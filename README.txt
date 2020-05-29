create database named "test"
run db script (dbscript.sql)
change database username and password in application.properties
run this command in console
   mvn clean package
run this command in console
   mvn clean spring-boot:run
you can try to access these URLs
   To create new row in database
        http://localhost:8080/api/user/insertOK
   To create new row in database and throw exception (it will rollback)
        http://localhost:8080/api/user/insertNotOK
   To see rows from database
        http://localhost:8080/api/user/list