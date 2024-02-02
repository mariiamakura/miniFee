# Delivery Fee Calculator

### Prerequisites
- Java 17 or higher

### Build
Run the following command to build the project:
```
./gradlew build
```

After building the project, run the following command to start the application:
```
java -jar build/libs/final-demo-0.0.1-SNAPSHOT.jar
```

The application will start, and the POST endpoint 
will be available at http://localhost:8080/api/order by default.

### Customizing Server Configuration
You can customize these settings in the "src/main/resources/application.properties" file:
```
server.port=8080
server.address=0.0.0.0
```

### Example
Endpoint: http://localhost:8080/api/order

#### Request:
```
{"cart_value": 790, "delivery_distance": 2235, "number_of_items": 4, "time": "2025-01-15T13:00:00Z"}
```
#### Expected Response:
```
{"delivery_fee": 710}
```
