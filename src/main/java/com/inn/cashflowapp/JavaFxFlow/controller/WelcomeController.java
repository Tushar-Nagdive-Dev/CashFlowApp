package com.inn.cashflowapp.JavaFxFlow.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    public void onViewExpenses(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/expenses.fxml"));
            Parent root = loader.load();

            // Get the stage from the button triggering the event
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddExpense() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Expense");
        alert.setHeaderText(null);
        alert.setContentText("Navigating to Add Expense...");
        alert.showAndWait();
    }
}
