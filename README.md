# Action Monitor Application


## 1. Installation
  1. mvn clean package -f action-monitor/pom.xml
  2. docker build  -t action-monitor:2.0.0 ./action-monitor/
  3. cd react-client
  4. npm install 
  5. docker build -t react-client:3.0.0 .
  6. cd ..
  7. docker-compose up

## 2. Usage
  As a client you can send messages to topic for all users and personal messages
  if you want to monitor all the messages you can login with username super-admin
  to use the client after running the docker-compose connect to http://localhost:3000
  
## 3 Useful URL
  1. http://localhost:8080/actuator/info -> application info
  2. http://localhost:8080/actuator/health -> application health
  3. http://localhost:8080/h2-console -> application database
     Driver class  -> org.h2.Driver
     JDBC URL      -> jdbc:h2:mem:mydb
     user Name     -> sa
     password      -> password
