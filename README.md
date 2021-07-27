See the configs for Front-End and Back-End Here.

**For Front-End configuration you need to** <br />
1.keep port:4200 free to use<br />
2.run "npm -install"<br />

**For Back-End configuration you need to** <br />
1. Keep port:8080 free to use<br />
2. Set the database as describe
#Create database by running the code below**<br />
```CREATE DATABASE `busyqacrm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;```<br />
```INSERT INTO `busyqacrm`.`tbl_roles` (`id`, `description`, `name`) VALUES ('1', 'Admin', 'Admin');
INSERT INTO `busyqacrm`.`tbl_roles` (`id`, `description`) VALUES ('2', 'Manager','Manager');
INSERT INTO `busyqacrm`.`tbl_roles` (`id`, `description`) VALUES ('3', 'User','User');

3. Type the "application.properties" file as below<br />


```server.port=8080
server.servlet.context-path=/CRMBackend

spring.datasource.url = jdbc:mysql://localhost:3306/busyqacrm
spring.datasource.username=root
spring.datasource.password=Hx123456


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

crmbackend.app.jwtSecret= HexiaoSecretKey
crmbackend.app.jwtExpirationMs= 86400000


debug=false

trace=false


```





