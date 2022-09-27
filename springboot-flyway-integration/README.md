# Flyway DB integration with Spring Boot

This repo demonstrates how to integrate Flyway with Spring Boot. 
The example uses MariaDB. 

To start a MariaDB instance, use this command:

```shell
docker run --rm -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -p 3306:3306 mariadb:10.3
```

You can use other database/version if you prefer. 

Detailed tutorial is available here: [How to integrate Flyway with Spring Boot](https://datmt.com/backend/java/spring/flyway-spring-boot-integration-guide/)