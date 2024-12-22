package com.inn.cashflowapp.SpringFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inn.cashflowapp.JavaFxFlow.FxMain;

import javafx.application.Application;

@SpringBootApplication
public class CashflowappApplication {

    public static void main(String[] args) {
        // Start Spring Application
        SpringApplication.run(CashflowappApplication.class, args);

        // Start JavaFX Application
        Application.launch(FxMain.class, args);
    }

    
}
