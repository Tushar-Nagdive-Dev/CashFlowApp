package com.inn.cashflowapp.JavaFxFlow.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

import com.inn.cashflowapp.JavaFxFlow.models.Expense;

public class ExpensesController {

    @FXML
    private TableView<Expense> expensesTable;

    @FXML
    private TableColumn<Expense, Integer> idColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableColumn<Expense, Double> amountColumn;

    @FXML
    private TableColumn<Expense, LocalDate> dateColumn;

    @FXML
    public void initialize() {
        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Populate table with sample data
        ObservableList<Expense> expenses = FXCollections.observableArrayList(
            new Expense(1, "Groceries", 50.0, LocalDate.now().minusDays(1)),
            new Expense(2, "Transport", 15.5, LocalDate.now().minusDays(2)),
            new Expense(3, "Entertainment", 100.0, LocalDate.now())
        );
        expensesTable.setItems(expenses);
    }

    @FXML
    public void onBackToWelcome() {
        // Logic to navigate back to the welcome page
    }
}