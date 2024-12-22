# Expense Management Application - End-to-End Documentation

This document provides a detailed guide for the Expense Management Application built using **Spring Boot**, **JavaFX**, **SQLite**, and **Tailwind CSS**. It covers the project structure, setup, implementation, and how to run the application successfully.

---

## **1. Project Overview**
The Expense Management Application is a desktop application that integrates a JavaFX UI with a Spring Boot backend. It uses SQLite for lightweight database management and Tailwind CSS for styling JavaFX components.

### **Key Features**
- Dynamic UI using JavaFX.
- Backend services powered by Spring Boot.
- Lightweight database with SQLite.
- Predefined schema initialization with Hibernate.
- Styled UI using Tailwind CSS.

---

## **2. Project Structure**

```
ExpenseManagementApp/
├── database/                           # SQLite database storage
│   └── expense_management.db
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── inn/
│   │   │           └── cashflowapp/
│   │   │               ├── JavaFxFlow/
│   │   │               │   ├── FxMain.java             # JavaFX entry point
│   │   │               │   ├── controller/
│   │   │               │   │   └── WelcomeController.java # JavaFX controller
│   │   │               │   └── models/                # (Optional) JavaFX models
│   │   │               └── SpringFlow/
│   │   │                   ├── CashflowappApplication.java # Spring Boot entry point
│   │   │                   ├── config/
│   │   │                   │   └── DataSourceConfig.java   # DataSource configuration
│   │   │                   ├── controller/            # REST controllers
│   │   │                   ├── model/                 # JPA entities
│   │   │                   ├── repository/            # JPA repositories
│   │   │                   └── service/               # Business logic
│   │   └── resources/
│   │       ├── fxml/
│   │       │   └── main.fxml                         # JavaFX UI layout
│   │       ├── css/
│   │       │   ├── output.css
│   │       │   ├── styles.css
│   │       │   └── tailwind.css
│   │       ├── application.properties               # Spring Boot configuration
│   │       ├── schema.sql                           # Database schema initialization
│   │       ├── static/                              # Static assets
│   │       └── templates/                           # Additional templates
│   └── test/
│       └── java/
│           └── com/
│               └── inn/
│                   └── cashflowapp/
│                       └── CashflowappApplicationTests.java
├── javafx-sdk-21.0.5/                              # JavaFX SDK
├── pom.xml                                         # Maven build file
└── tailwind.config.js                              # Tailwind CSS configuration
```

---

## **3. Application Setup**

### **Step 1: Install Prerequisites**
- **Java JDK 17 or later**.
- **Maven** for project build and dependency management.
- **SQLite JDBC Driver** for database connectivity.
- **JavaFX SDK** for UI components.
- **Node.js and npm** for Tailwind CSS integration.

### **Step 2: Install Dependencies**
Update the `pom.xml` file with required dependencies:

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- SQLite -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.42.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-community-dialects</artifactId>
    </dependency>

    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.5</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21.0.5</version>
    </dependency>

    <!-- Lombok for code simplification -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### **Step 3: Configure Database**
**File: `application.properties`**

```properties
# Application Info
spring.application.name=cashflowapp
server.port=8082

# Database Configuration
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.url=jdbc:sqlite:database/expense_management.db
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Schema Initialization
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql
```

### **Step 4: Initialize Tailwind CSS**
1. Install Tailwind:
   ```bash
   npm install -D tailwindcss postcss autoprefixer
   ```
2. Configure Tailwind:
   ```javascript
   module.exports = {
       content: [
           "./src/main/resources/**/*.fxml",
           "./src/main/resources/**/*.css"
       ],
       theme: {
           extend: {},
       },
       plugins: [],
   };
   ```
3. Build CSS:
   ```bash
   npm run build:css
   ```

---

## **4. Key Implementations**

### **4.1 JavaFX UI**
**File: `main.fxml`**
```xml
<VBox xmlns:fx="http://javafx.com/fxml" alignment="CENTER" spacing="20" fx:controller="com.inn.cashflowapp.JavaFxFlow.controller.WelcomeController">
    <Label text="Welcome to Expense Management App" />
    <Button text="Click Me" onAction="#onButtonClick" />
</VBox>
```

**File: `WelcomeController.java`**
```java
package com.inn.cashflowapp.JavaFxFlow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class WelcomeController {

    @FXML
    public void onButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to the Expense Management App!");
        alert.showAndWait();
    }
}
```

### **4.2 Spring Boot Backend**
**File: `DataSourceConfig.java`**
```java
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        return dataSource;
    }
}
```

---

## **5. Running the Application**

### **Step 1: Clean and Build**
```bash
./mvnw clean install
```

### **Step 2: Run the Application**
```bash
./mvnw spring-boot:run
```

### **Step 3: Verify Output**
- The JavaFX window should display the welcome message.
- The SQLite database file should be created in the `database/` directory.
- Spring Boot APIs should respond on `http://localhost:8082/`.

---

## **6. Testing**
Run the following command to execute unit tests:
```bash
./mvnw test
```

---

## **Conclusion**
This document outlines the complete setup, configuration, and implementation of the Expense Management Application. If you encounter any issues, verify the directory structure, dependencies, and configurations.

