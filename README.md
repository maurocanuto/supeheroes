# Superheroes
This is a basic application that implements a RESTFUL web service that allows to create and retrieve information about Superheroes with the following properties:
* Name
* Pseudonym
* The publisher it is from (e.g. DC or Marvel)
* List of "skills" or powers
* List of allies (e.g Batman's ally would be Robin) if the superhero has any
* Date of first appearance (in the format YYYY-MM-DD)

It is possible to:
* Get a list of all superheros in JSON format
* Get the details of a specific superhero in JSON format
* Delete a specific superhero

## Frameworks
### Backend
This application has been developed using Java 7.
Since this is a simple stand-alone web application I decided to use Spring boot (Spring  Web 4.2.5) which comes with an embedded HTTP servers and Servlet Containers (Tomcat in my case) and with no need to deploy WAR files.
In order to generate a basic project structure I used Spring Initializr (v. 1.3.5). This tool does not generate any application code, but it just give you a basic project structure and either a Maven or a Gradle build specification. I use Maven in this case.
This application uses Java Persistence API (JPA) for the management of relational data. JPA allows you to avoid writing DML in the database specific dialect of SQL and to express the queries in terms of the Java entities rather than the (native) SQL tables and columns.
In this case I use MySQL as RDBMS.

### Frontend
The front end has been developed using AngularJS and it is a very basic web interface.

## REST Path:
* Get all the superheroes: /getAllSuperheroes
* Get info about a specific hero: /getHero/{name}
* Remove a specific hero: /removeHero/{name}
