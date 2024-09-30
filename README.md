# Simple-Search-Engine
A simple Java-based search engine using Servlets, JSP, and SQL Database.This application allows users to search for websites based on keywords, displaying relevant results from a pre-populated database. It also maintains a history of searched pages and user interactions.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Set Up the Database](#2-set-up-the-database)
  - [3. Configure the Project](#3-configure-the-project)
  - [4. Deploy to Apache Tomcat](#4-deploy-to-apache-tomcat)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- **Search Functionality**: Users can enter keywords to search for relevant websites.
- **Top 30 Results**: Displays the top 30 websites matching the search criteria.
- **Search History**: Maintains a history of searched pages.
- **User Accounts**: Links user accounts with search history.
- **File Operations**: Save and retrieve search data.
- **Responsive UI**: User-friendly interface built with Java Swing and JSP.

## Technologies Used

- **Java**: Core programming language.
- **Java Servlets**: Handle HTTP requests and responses.
- **JSP (JavaServer Pages)**: Render dynamic web content.
- **JDBC (Java Database Connectivity)**: Connects Java application to the database.
- **SQL Database**: Oracle or MySQL to store website data.
- **Apache Tomcat**: Servlet container to deploy and run the web application.
- **SOIF (Summary Object Interchange Format)**: Format for resource description.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK)** installed (version 8 or higher).
- **Apache Tomcat** installed and configured.
- **Git** installed on your machine.
- **SQL Database** (Oracle or MySQL) installed and running.
- **IDE**: Visual Studio Code with Java Extension Pack installed.

## Installation

### 1. Clone the Repository

Open your terminal or command prompt and clone the repository using Git:

```bash
git clone https://github.com/yourusername/SimpleSearchEngine.git
```

Navigate to the project directory:

```bash
cd SimpleSearchEngine
```

### 2. Set Up the Database

#### a. Create the Database

1. **Open your SQL client** (e.g., MySQL Workbench, Oracle SQL Developer).
2. **Create a new database** named `search_engine_db`.

   ```sql
   CREATE DATABASE search_engine_db;
   ```

3. **Use the database**:

   ```sql
   USE search_engine_db;
   ```

#### b. Create the `Websites` Table

```sql
CREATE TABLE Websites (
    id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    keywords VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### c. Populate the `Websites` Table

Insert sample data into the `Websites` table:

```sql
INSERT INTO Websites (url, description, keywords) VALUES
('https://www.example.com', 'An example website for demonstration.', 'example, demo'),
('https://www.sample.com', 'A sample website showcasing various features.', 'sample, features'),
('https://www.javaprogramming.com', 'Resources and tutorials for Java programming.', 'java, programming, tutorials'),
('https://www.wikipedia.org', 'Free online encyclopedia.', 'encyclopedia, information'),
('https://www.github.com', 'GitHub is a code hosting platform.', 'coding, hosting, version control');
```

*You can add more entries as needed.*

### 3. Configure the Project

#### a. Update Database Credentials

1. Navigate to the `src/com/example/SearchServlet.java` file.
2. Update the database connection parameters with your database details:

   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/search_engine_db"; // Change to your DB URL
   private static final String DB_USER = "your_db_username"; // Your DB username
   private static final String DB_PASSWORD = "your_db_password"; // Your DB password
   ```

#### b. Add Servlet API to Project

If you're not using Maven, ensure the `javax.servlet-api-4.0.1.jar` is included in your project:

1. **Download the Servlet API JAR** from [Maven Repository](https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1).
2. **Create a `lib` folder** in your project root.
3. **Place the JAR** inside the `lib` folder.
4. **Configure Classpath**:
   - If using Visual Studio Code, ensure the `lib` folder is included in the classpath.
   - Update your build commands to include the JAR:

     ```bash
     javac -cp "lib/*" -d bin src/com/example/SearchServlet.java
     ```

### 4. Deploy to Apache Tomcat

#### a. Package the Application as a WAR File

1. **Navigate to the project root directory**:

   ```bash
   cd SimpleSearchEngine
   ```

2. **Create a WAR file** using the following command:

   ```bash
   jar -cvf SimpleSearchEngine.war -C bin/ . -C WebContent/ .
   ```

   - Ensure you have a `WebContent` folder containing your JSP files and `WEB-INF` directory with `web.xml` and `lib` folder.

#### b. Deploy the WAR File to Tomcat

1. **Copy the WAR file** to the Tomcat `webapps` directory:

   ```bash
   copy SimpleSearchEngine.war C:\apache-tomcat-<version>\webapps\
   ```

   - Replace `<version>` with your actual Tomcat version number.

2. **Start Tomcat**:

   - Open Command Prompt as Administrator.
   - Navigate to Tomcat's `bin` directory:

     ```bash
     cd C:\apache-tomcat-<version>\bin
     ```

   - Run the startup script:

     ```bash
     startup.bat
     ```

   - If you encounter issues, ensure `CATALINA_HOME` is set correctly.

3. **Verify Deployment**:

   - Open your web browser and navigate to:

     ```
     http://localhost:8080/SimpleSearchEngine/search
     ```

   - You should see the search interface of your application.

## Usage

1. **Access the Application**:

   Open your web browser and go to `http://localhost:8080/SimpleSearchEngine/search`.

2. **Perform a Search**:

   - Enter a keyword into the search bar.
   - Click the **Search** button.
   - View the top 30 websites related to your search term.

3. **View Search History**:

   - The application maintains a history of your searches.
   - You can view previously visited pages and manage your account links.

4. **Manage Your Account**:

   - If user accounts are implemented, you can link searches to specific accounts for personalized history tracking.

## Project Structure

```
SimpleSearchEngine/
├── bin/
│   └── com/
│       └── example/
│           └── SearchServlet.class
├── lib/
│   └── javax.servlet-api-4.0.1.jar
├── src/
│   └── com/
│       └── example/
│           └── SearchServlet.java
├── WebContent/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── lib/
│   │       └── javax.servlet-api-4.0.1.jar
│   ├── index.jsp
│   └── result.jsp
├── README.md
└── .gitignore
```

- **`bin/`**: Contains compiled `.class` files.
- **`lib/`**: Contains external library JARs (e.g., Servlet API).
- **`src/`**: Java source files.
- **`WebContent/`**: Web resources including JSP files and `WEB-INF`.
- **`README.md`**: Project documentation.
- **`.gitignore`**: Specifies files/directories to ignore in Git.

## Contributing

Contributions are welcome! Follow these steps to contribute to the project:

1. **Fork the Repository**:

   Click the **Fork** button at the top-right corner of this repository's GitHub page.

2. **Clone Your Fork**:

   ```bash
   git clone https://github.com/lux-2002/SimpleSearchEngine.git
   ```

3. **Create a New Branch**:

   ```bash
   git checkout -b feature/YourFeatureName
   ```

4. **Make Your Changes**:

   Implement your feature or bug fix.

5. **Commit Your Changes**:

   ```bash
   git commit -m "Add feature: Description of the feature"
   ```

6. **Push to Your Fork**:

   ```bash
   git push origin feature/YourFeatureName
   ```

7. **Create a Pull Request**:

   - Go to your fork on GitHub.
   - Click the **Compare & pull request** button.
   - Provide a clear description of your changes and submit the pull request.
