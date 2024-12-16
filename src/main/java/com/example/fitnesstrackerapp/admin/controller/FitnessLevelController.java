package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FitnessLevelController implements Initializable {
    @FXML
    public Label lb_lvl;
    @FXML
    private Button btn_beginner;
    @FXML
    private Button btn_intermeddiate;
    @FXML
    private Button btn_advanced;
    @FXML
    public Button btn_cont;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disable the continue button initially
        btn_cont.setDisable(true);

        // Add action listeners for each fitness level button
        btn_beginner.setOnAction(event -> handleButtonSelection(btn_beginner));
        btn_intermeddiate.setOnAction(event -> handleButtonSelection(btn_intermeddiate));
        btn_advanced.setOnAction(event -> handleButtonSelection(btn_advanced));

    }

    // Method to handle button selection logic
    private void handleButtonSelection(Button selectedButton) {
        // Deselect all buttons first
        resetButtonStyles();

        // Highlight the selected button
        selectedButton.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white;");

        // Enable the continue button
        btn_cont.setDisable(false);
        btn_cont.setOnAction(event -> {
                    UserService userService = new UserService();
                    userService.changeScence1(event,"howOften_view.fxml", "Make us know you better");
    });}

    // Helper method to reset button styles
    private void resetButtonStyles() {
        btn_beginner.setStyle("");
        btn_intermeddiate.setStyle("");
        btn_advanced.setStyle("");
    }
}
