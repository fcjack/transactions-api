### Web Transactions Project

This project is a code test to work with Java 8 and Spring to manipulate
data in memory, during the last 60 seconds.

The test need to have an API with two endpoints, ``/transaction`` and ``/statistics``

The endpoint ``/transaction`` is prepared to receive a POST request with the model transaction on body,
to store a new transaction in memory.

The endpoint ``/statistics`` will receive a GET request and return the model Statistics with some data about
the transactions received and stored during the last 60 seconds.

## Documentation

The project contains the Swagger 2 enabled, and you can see the endpoints and
a small representation of parameters on Swagger-UI.
After start the project, you can access ``http://localhost:8080/swagger-ui.html``.

## Model

The models of the project are simple, basically two entities:

``` 
    Transaction
    {
        "amount": <Double value>,
        "timestamp": <long value> (EPOCH TIME ON UTC)
    }
    
    Statistics 
    {
        "sum":   <Double value>,  
        "avg":   <Double value>,  
        "max":   <Double value>,  
        "min":   <Double value>, 
        "count": <Long value>
    
    }
    
```

## Build project

The project is a maven project that you can build just running the command ``mvn clean install``
and as the result of the instruction you will have an artifact .jar file inside the folder target

To run the project, you can run ``mvn spring-boot:run`` and the project will be built and started.
If you prefer change the port of the project you can add the instruction ``server.port`` on application.properties
file on resources folder, after that you can rebuild and start the application.

The default port of the project is 8080. So, to consume the API all the request will be in format: 
http://<server.host>:<server.port>/{endpoint}