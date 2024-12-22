package com.inn.cashflowapp.SpringFlow.config;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource datasource() {
        // Ensure database file exists
        ensureDatabaseFileExists(env.getProperty("url"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    private void ensureDatabaseFileExists(String url) {
        if (url == null || !url.startsWith("jdbc:sqlite:")) {
            throw new IllegalArgumentException("Invalid SQLite URL: " + url);
        }

        // Extract the file path from the JDBC URL
        String dbPath = url.replace("jdbc:sqlite:", "");

        File databaseFile = new File(dbPath);
        try {
            // Create parent directories if they do not exist
            if (databaseFile.getParentFile() != null && !databaseFile.getParentFile().exists()) {
                databaseFile.getParentFile().mkdirs();
                log.info("Database directory created: {}", databaseFile.getParentFile().getAbsolutePath());
            }

            // Create the database file if it does not exist
            if (!databaseFile.exists()) {
                databaseFile.createNewFile();
                log.info("Database file created: {}", databaseFile.getAbsolutePath());
            }
        } catch (IOException e) {
            log.error("Failed to create database file: {}", e.getMessage());
            throw new RuntimeException("Could not initialize database file", e);
        }
    }
}
