# javafx-consuming-rest-service
Demo for JavaFX consuming REST web service in Spring Boot

## sis-service
1. Run `database.sql` script.
2. Update MySQL `user` and `password` in `application.yml` depending on your MySQL credentials.
3. Spring Boot 3.2+ requires Java 21.
    - Update `run.sh` for your JDK 21 location and execute `./run.sh`; 
    - Or, you can just run: `./mvnw clean package && java -run ./target/sis-service-0.0.1-SNAPSHOT.jar`

## sis-desktop
1. Requires Java 17.
2. Update `run.sh` for your JDK 17 location  and execute `./run.sh`; 