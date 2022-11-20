## Framework choices
Micronaut framework is used. Here are some references

- [User Guide](https://docs.micronaut.io/3.7.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.7.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.7.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

For the build, it is done with Gradle.

---
The Micronaut framework is used for:
- Application bootstrap
- Dependency injection
- Rest API
- Tests

---
## Solution
The requested functions are implemented in
- `walidjek.glady.deposit.DepositService.distribute()`
- `walidjek.glady.user.getBalance()`

A rest API was also added to easily test the project. It's contained in the controllers:
- `walidjek.glady.api.deposit.DistributionRequest`
- `walidjek.glady.api.user.UserController`

## Architecture
- The project is split into 4 main packages:
  - Company: contains the Company service, domain and data access.
  - Deposit: contains the Deposit service and domain. No data access was implemented for the deposits.
  - User: contains the User service, domain and data access.
  - Api: contains some endpoints to test the application
- hexagonal architecture:
  - A hexagonal architecture is implemented, where the controllers, business logic and data access are separated.
  - Example:
    - The Rest API doesn't use the business logic domain
    - A `UserRepository` interface is used by the business logic
    - An in-memory storage is implemented
  - Note: to avoid an over-engineering for a simple project, the business logic domain is used to store the data

## JUnit tests
Some tests were written, they don't cover all the application, but only the main functions
- An integration test `DepositServiceTest` to test the distribute function, using the `MicronautTest`
- Some unit tests
- `Mockito` was used as mock system

## Run & Test the application
To run the tests, execute: `./gradlew test`.<br />
And to run the application, execute `./gradlew run`. The application is launched on `http://localhost:8080`

### Dataset
An initial dataset is created containing a company with the id `123` and a balance of `1000`.<br />
It also contains a user with the id `456` and some deposits

### requests
We can start first by retrieving the user with the request:

```
curl --request GET 'http://localhost:8080/api/user/456'
```
This will return the user and its balances.
```
{
    "id": 456,
    "name": "Walid",
    "gift_balance": 250,
    "meal_balance": 90
}
```
Next, we can try to distribute a deposit gift, using the following request:
```
curl --request POST 'http://localhost:8080/api/deposit/gift' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": "150",
    "company_id": 123,
    "user_id": 456
}'
```
Or, we can use the endpoint `/api/deposit/meal` for a meal gift.<br />
We can check the new balance of the user with the previous endpoint `/api/user/456`.

**Note:**
The data can be reset using the endpoint `post /api/reset`

## Error handling
- A http 404 response is sent back when an unknown user or company is requested
- A http 403 response is sent if the company balance is insufficient when distributing a deposit 

## What else
No security was implemented, but a use of Micronaut-security can be done.
