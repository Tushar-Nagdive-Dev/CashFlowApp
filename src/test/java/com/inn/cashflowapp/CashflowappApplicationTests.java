package com.inn.cashflowapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.inn.cashflowapp.SpringFlow.CashflowappApplication.class)
class CashflowappApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        // Ensures the Spring application context loads successfully
    }

    @Test
    void testDataSourceConnection() {
        // Verify that the DataSource is correctly initialized
        assertNotNull(dataSource, "DataSource should not be null");

        try (var connection = dataSource.getConnection()) {
            assertNotNull(connection, "Database connection should not be null");
            System.out.println("Database connection is successful: " + connection.getMetaData().getURL());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
