# BusyQA-CRM
This project contains BusyQA-CRM front-end and back-end


For **Front-End** configuration please run <br />
```npm install``` <br />
first to install node_module and all other dependent component. <br />
please make sure that your **Port-4200** not occupied by other program.
<br /><br /><br />
For **Back-End** configuration please <br />
**1. Firstly configure your DB schema by importing the script file in the root folder of repo.**<br />
**2. Make sure there are no program running on port - 8080**<br />
**3. Application.properties - Make sure you have meet all requirement and with correct DB username and password and port number**

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



**4. Create database by running the code below**<br />
```CREATE DATABASE `busyqacrm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;```<br />
**5. You can run the Junit-test if table not shows in when the first time server start**<br />
**6. Please run the SQL command to insert data into Role table**<br />
```INSERT INTO `busyqacrm`.`tbl_roles` (`id`, `description`, `name`) VALUES ('1', 'Admin', 'Admin');
INSERT INTO `busyqacrmbackenddb`.`tbl_roles` (`id`, `description`) VALUES ('2', 'Manager','Manager');
INSERT INTO `busyqacrmbackenddb`.`tbl_roles` (`id`, `description`) VALUES ('3', 'User','User');```


