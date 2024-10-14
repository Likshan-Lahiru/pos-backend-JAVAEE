
# **Jakarta EE Application with MySQL and AJAX**

## **Project Overview**
This project demonstrates a multi-layered architecture using **Jakarta EE**, **MySQL**, and **AJAX** (with `fetch()` as the preferred mechanism). The application maintains clear separation of concerns with a layered structure (presentation, business logic, and data access), ensuring maintainability and scalability.

Key features of this project include:
- **Database integration using JNDI**.
- **Native SQL queries** for performing database operations.
- **Proper logging setup** with appropriate logging levels (INFO, DEBUG, ERROR, etc.).
- Detailed **API documentation** that is linked directly to this README.

## **Tech Stack**
- **Backend**: Jakarta EE
- **Frontend**: AJAX (with `fetch()`), HTML, CSS, JavaScript
- **Database**: MySQL (configured using JNDI)
- **Logging**: Java Logging API or a framework like Log4j (whichever is applicable)

## **Project Architecture**
The application follows a strict layered architecture:
1. **Presentation Layer**: Handles user interface and user interaction. This layer uses AJAX (`fetch()`) to communicate with the backend APIs.
2. **Business Logic Layer**: Contains the core business logic. It separates concerns by providing services that handle business rules.
3. **Data Access Layer**: Responsible for interacting with the MySQL database. It uses native SQL queries to perform CRUD operations.
4. **Configuration Layer**: Uses JNDI for database connection management and handles other configurations.

## **Key Features**
- **JNDI-based Database Configuration**: Provides a centralized and efficient way of managing database connections.
- **Native SQL Queries**: Ensures control and optimization of database interactions.
- **AJAX-based Frontend**: Implements asynchronous API calls using `fetch()` for a smoother user experience.
- **Proper Logging**: Logs messages at appropriate levels (INFO, DEBUG, ERROR) to assist with debugging and monitoring.
- **API Documentation**: The APIs are documented and accessible via the link provided below.

## **Logging**
The application uses a logging framework to manage logs with various levels:
- **INFO**: To track the general flow of the application.
- **DEBUG**: For detailed debugging information.
- **ERROR**: To log any critical failures or issues.

Make sure to check the configuration file for logging settings.

sponse formats.

## **Project Setup**
To set up this project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/Likshan-Lahiru/pos-backend-JAVAEE.git
   ```
2. Configure the JNDI database connection in your application server (e.g., GlassFish or WildFly).
3. Build the project using Maven or your preferred build tool.
4. Deploy the application to your Jakarta EE-supported server.

## **Contributing**
Please follow the best coding practices:
- Maintain a clean, readable codebase.
- Apply SOLID principles where applicable.
- Write meaningful commit messages and keep pull requests small and focused.

## **License**
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for more details.
