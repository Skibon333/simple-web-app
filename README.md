### Simple Web Application
Test task for Godel Technologies Europe.



### Practical task:
- Using the provided skeleton, implement the REST service.
- Useful link: https://spring.io/guides/gs/rest-service
- In addition you could use Swagger to provide API documentation.
  
  
  
### How to start:
1. Install the latest version of docker if you still haven't done it.
2. Run ActiveMq Docker Container in terminal using instructions below:
   - docker pull rmohr/activemq
   - docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
3. Run MySQL Docker Container or just use the PC client MySQL Workbench.
4. Execute script.sql.
5. Run the application and check end-points work using Postman client or this URL:
   - http://localhost:8080/swagger-ui.html
  
  
  
### Technologies:
- Programming language: Java 9;
- Frameworks:
  - Spring (Boot, Core, Data, Web, Test);
  - Hibernate.
- Query language: SQL;
- IDE: IntelliJ IDEA;
- Database: MySQL;
- Message Broker: ActiveMQ;
- Tools: JUnit 4, Mockito, Lombok, Postman, Swagger 2, Maven, Git, Docker, JDBC, JPA, JMS, HTTPs, XML, YAML, JSON;
- Others: GitHub.