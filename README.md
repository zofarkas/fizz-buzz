# Fizz Buzz web app

### Requirements
 - Java 11
 - Maven 3.6.0+

### How to build
The application can be built using `mvn clean verify`. This will compile the code and run all unit and acceptance tests.
To generate the executable JAR file, run `mvn clean package spring-boot:repackage` from the `web` folder, `fizz-buzz.jar` will be created. 

### How to run
You can run the application from the projects main directory using the following command:
```
java -jar web/target/fizz-buzz.jar --server.port=8080
```
The `--server.port` is optional, without it the application will start on port `8080`.

### How to use
The application has a single HTTP GET endpoint to generate the Fizz Buzz sequence. It requires a `lastElement` positive integer request parameter.
No need to specify any request header, the server will return the result in JSON format.

Example request:
```
http://localhost:8080/fizz-buzz?lastElement=5
```
Response body:
```json
{
    "data": [
        "1",
        "2",
        "Fizz",
        "4",
        "Buzz"
    ]
}
```

### Notes
##### Multiple modules
Three modules have been created
 - **web**: contains the application entry point and the controller which serves the fizz-buzz endpoint
 - **service**: the business logic can be found in this module
 - **acceptance-tests**: the module of high level tests
 
With this separation of code, we can have clear boundaries. (No HTTP request handling in the service module)

##### Configuration classes
I don't think that autowiring would cause trouble in this small application but I prefer creating Configuration classes instead of relying on `@Autowired`.

##### `FizzBuzzSequenceService` and `FizzBuzzFragmentService`
The initial idea was to simply implement the whole logic in a class with a for loop and with conditional statements in it.
It would violate the Single Responsibility and Open-closed principles from SOLID, this is why I separated the logic into so many classes.
In `AbstractDelegatingFragmentProvider`, I have implemented two Design Patterns.
 - Chain of responsibility, to let the providers either generate the Fizz Buzz fragment or pass it to the next handler in the chain
 - Template method, to specify the skeleton of the fragment resolution logic and let the implementations decide if they are applicable for the input
 
`FizzBuzzSequenceService` has one implementation `DefaultFizzBuzzSequenceService`, to simply iterate from 1 to the last index, generate the fragments and return them as a sequence.
Another implementation could be added, which can parallelize this work, which must keep the ordering of the elements. Caching implementation can be added as well.

I have created value objects as these are more meaningful than returning `String` and `List<String>`.

### Response body
I have decided to create a response object with a list in it for the sequence instead of returning a plain list. 
With this solution, additional data can be added to the response later without breaking backward compatibility.

### Things to consider or improve
 - Lombok can be used to remove boilerplate code
 - Swagger can be added to describe the endpoint
 - Specific exceptions can be created and react to them in the controller (e.g.: `InvalidLastElementException`)
 - Error handling from `FizzBuzzController` could be moved to a separate class to reduce responsibilities
 - Check if there is a more sophisticated solution for comparing response to expected json files in acceptance tests
 - `acceptance-tests` module depends on `web` module. When I add the `repackage` goal to the `spring-boot-maven-plugin` 
 (to create executable JAR without running the separate command) the "fat" JAR will used by the acceptance-tests and it won't find symbol `FizzBuzzApplication`