# Bank Account Application

This project is a **Bank Account Application** built using **Hexagonal Architecture (Ports and Adapters)**. It is designed to follow **Domain-Driven Design (DDD)** principles and adhere to **clean code** standards, ensuring maintainability, scalability, and testability.
This project is for demonstration purposes only.

---

## Features

- **Account Management**: Create and manage accounts with details like account ID, owner, and balance.
- **Transactions**: Handle money transfers between accounts while enforcing business rules.
- **Infrastructure**: Adapters for database persistence and RESTful API exposure.
- **Validation**: Input validation using annotations for DTOs.
- **Separation of Concerns**: Independent layers for application logic, domain logic, and infrastructure.

---

## Technologies Used

### Backend:
- **Java**: The primary programming language.
- **Spring Boot**: Framework for RESTful APIs and dependency injection.
- **Jakarta Validation**: For input validation.
- **JPA/Hibernate**: ORM for database interactions.
- **JUnit 5 & Mockito**: For unit and integration testing.

### Architecture:
- **Hexagonal Architecture**: Ensures separation of concerns and flexibility.
- **Domain-Driven Design (DDD)**: Emphasis on the core domain and domain logic.

---

## Project Structure

```plaintext
src/
├── main/
│   ├── java/com/sgkata/bankaccount/
│   │   ├── application/      # Application layer (DTOs, Ports)
│   │   ├── domain/           # Domain layer (Entities, Services, Exceptions)
│   │   ├── infrastructure/   # Infrastructure layer (Adapters, Mappers, Repositories)
│   │   └── BankAccountApp.java # Main application entry point
│   └── resources/
│       ├── application.properties  # Configuration
│       └── data.sql                 # Sample data
├── test/
│   └── java/com/sgkata/bankaccount/ # Test cases for all layers
```

---

## Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **PostgreSQL/MySQL** (or your preferred database)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/bank-account-app.git
   ```

2. Navigate to the project directory:
   ```bash
   cd bank-account-app
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the API at:
   ```
   http://localhost:8080
   ```

---

## API Endpoints

### Transactions
- **Send Money**: `POST /accounts/send/{sourceAccountId}/{targetAccountId}/{amount}`

---

## Testing

1. Run unit tests:
   ```bash
   mvn test
   ```

---

## Contributing

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes with descriptive messages.
4. Open a pull request to the `main` branch.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Contact

For any questions or suggestions, please contact:

- **GitHub**: [your-username](https://github.com/your-username)

---

Happy coding! 🚀

