package com.inn.cashflowapp.JavaFxFlow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class WelcomeController {

    @FXML
    public void onViewExpenses() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Navigation");
        alert.setHeaderText(null);
        alert.setContentText("Navigating to 'View Expenses' Page...");
        alert.showAndWait();
        // Implement navigation logic here
    }

    @FXML
    public void onAddExpense() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Navigation");
        alert.setHeaderText(null);
        alert.setContentText("Navigating to 'Add Expense' Page...");
        alert.showAndWait();
        // Implement navigation logic here
    }
}
