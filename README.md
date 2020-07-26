# Backend Hospitals Administration

This project was make in Java 8, Spring boot 2.3.1 and Maven.

##Setup MySQL
Run the `schema.sql` for the initial script on the DB.

In th file `src/main/resources/application.properties`

change the next proterties by your sql credencial
`spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db`
`spring.datasource.username=root`
`spring.datasource.password=password`


## Running

Run `mvn clean install -DskipTests=true`.

Run `java -jar target/backend-hospital-0.0.1-SNAPSHOT.jar`.
