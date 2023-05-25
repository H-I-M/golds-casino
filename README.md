# golds-casino

This is a MVP POC Casino backend API for a casino start-up using :

* Spring boot
* H2 database
* Swagger 2
* Lombok

The H2 database console can be accesed on http://localhost:8080/h2 and the API endpoints on http://localhost:8080/swagger-ui/index.html ,or via a RESTClient (Postman) making calls to http://localhost:8080/casino .

# Note

The database is empty when the project is initially started, as a result a convenience post endpoint was added to create a new Player (http://localhost:8080/casino/player/create) that would allow you to further use the other endpoints.
Alternatively, the project contains a schema.sql file, uncomment the commented insert statements and start/restart the project, 2 records of test data will be added to the database. 
 N.B. When using the insert statements, because of the issue noted below (problems with GenerationType.IDENTITY and use of GenerationType.AUTO) the first 2 primary keys (1 & 2) will be occupied this can cause a 400 to  be returned when you press 'Execute'.
 This is resolved by hitting the 'Execute' button another 2-3 times (as the next available primary key would be 3). 

# Known issues

I'm having problems with GenerationType.IDENTITY on the id field for entity classes, this is due to an issue where the field has a setter method assigning null to the field value before persisting, throwing a org.springframework.dao.DataIntegrityViolationException.
To get this POC working I have had to switch the generation strategy to GenerationType.AUTO which is not ideal but gets things going, I will take a deeper look and resolve this when time allows. 
