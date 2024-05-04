# Amortization Schedule Service

### Prerequisites
- Java 21
- Maven
- IDE
- Port Number: 9001

### How to run this service?
1. **Clone the repository:**
2. **Open Project in your IDE**
3. **Run AmortisationScheduleServiceApplication**

### How to test?
- This service runs on port 9001, if there's any other service running on same port, please change the port number from application properties and try again.

- Open http://localhost:9001/swagger-ui/index.html in your browser, this is a swagger ui for this service.

- Test the API's provided.

- This application is connected to H2 Database, Open http://localhost:9001/h2-console in your browser, make sure JDBC= jdbc:h2:mem:testdb & username= sa
- Use H2 console to view tables and use queries


### Endpoints
1. http://localhost:9001/v1/amortization-schedule Creates a amortization schedule with LoanDetails as an request body.
2. http://localhost:9001/v1/amortizations/{id} Gets amortizations by a amortization schedule id as a path variable.
3. http://localhost:9001/v1/amortization-schedules Gets all amortization schedules.

#### Note: use swagger ui for API testing.