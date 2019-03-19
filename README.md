# video-rental-store

Simple component that allows to manage the rental administration for a video rental store.

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:
```
git@github.com:ajdafonte/video-rental-store.git
```

### Build and run component

Execute the following gradle task:
```
gradlew.bat build
```

### Running the tests

Execute the following gradle task:
```
gradlew.bat test
```

### Running the component
```
gradlew.bat bootRun
```

### Import into IDE

This is a Gradle project (build.gralde.kts). Here are some instructions on how to import a Gradle project in some of the most popular IDEs:
- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import
- Eclipse - https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

## Technical Decisions

In this section will be described some details about technical decisions made.

### Stack

- Java 8
- Spring Boot 2.1
- H2 Database 1.4
- Gradle 5.2.1

### Dependencies

- Spring Data JPA - For handling JPA persistence againts a relational database (H2 Database)
- Swagger - Framework that contains a set of core tools for designing, building, and documenting RESTful APIs. After running the component open the following url to see the available endpoint: http://localhost:8080/swagger-ui.html
- Testing libs: JUnit 5, Mockito, Hamcrest, Gson

### Database (DB) Model

In `src/main/resources/scripts/sql` the following scripts allows to define the DB schema and some initial data to populate the DB:
- data.sql
- schema.sql

Here's the DB Model:

![Alt text](/data/db-model.jpg?raw=true "DB Model")


### Available endpoints

For more details about these endpoints (input and output data) open http://localhost:8080/swagger-ui.html. Here's a short summary:

#### Customers

- `GET /customers` - Retrieve all the available customers.
- `GET /customers/{id}` - Retrieve a customer specified by the ID.
- `POST /customers` - Inserts a new customer in the system.
    - Input data: email and username
    - Constraints: Username is unique in the system

With this endpoints is possible to manage the customers in the system and also keep track of the customer "bonus" points.

#### Films

- `GET /films` - Retrieve all the available films.
- `GET /films/{id}` - Retrieve a film specified by the ID.
- `POST /films` - Inserts a new film in the system.
    - Input data: filmTypeId and name
    - Constraints: Name of the film is unique in the system

With this endpoints is possible to manage the films in the system.

#### Rentals

- `GET /rentals` - Retrieve all the available rentals.
- `POST /rentals` - Inserts a new rental in the system.
    - Input data: customerId, list of rental items (indicating the filmId and the number of days to rent the film)
    - Remarks: Input param 'customerId' - should be removed when API authentication/authorization is implemented
- `PATCH /rentals/{id}` - Patch a certain rental in the system.
    - Input data: list of rental items ids
    - Remarks
        - Currently, the only operation available is to return films from renting. But it can be extended in the future in other to handle other operations in the future. This was one of the reasons to use a PATCH method to implement the operation of returning films from renting;
        - This operation is not idempotent.

With this endpoints is possible to manage the rentals in the system and also calculate prices for rentals.


### General remarks & assumptions

- It was assumed that there's just one currency;
- It was assumed that this is an online rental store that serves physical film copies and there's an infinite stock of films;
- It was assumed that the customer pays the rental when renting a set of films, and if necessary, pays for the extra days when the film is returned;
- The following aspects were not considered during the development of this component and should be considered in the next stages:
    - Endpoints to manage information about the available film types and respective prices
    - API authentication/authorization
    - Caching
    - Transaction Management
