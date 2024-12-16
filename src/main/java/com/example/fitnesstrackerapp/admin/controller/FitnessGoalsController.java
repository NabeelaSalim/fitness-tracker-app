package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FitnessGoalsController implements Initializable {
    @FXML
    public Label lb_fg;
    @FXML
    public Button btn_cont;
    @FXML
    private CheckBox cBox_bm;
    @FXML
    private CheckBox cBox_gl;
    @FXML
    private CheckBox cBox_gs;
    @FXML
    private CheckBox cBox_mcs;
    @FXML
    private CheckBox cBox_ies;
    @FXML
    private CheckBox cBox_ec;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Disable the "Continue" button by default
        btn_cont.setDisable(true);

        // Set up event handlers for each checkbox
        cBox_bm.setOnAction(event -> handleCheckBoxClick());
        cBox_gl.setOnAction(event -> handleCheckBoxClick());
        cBox_gs.setOnAction(event -> handleCheckBoxClick());
        cBox_mcs.setOnAction(event -> handleCheckBoxClick());
        cBox_ies.setOnAction(event -> handleCheckBoxClick());
        cBox_ec.setOnAction(event -> handleCheckBoxClick());

        // Set up the "Continue" button's action
        btn_cont.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"welcome_view.fxml", "Starting and navigating your fitness journey");

        });

    }

    private void handleCheckBoxClick() {
        // Check if at least one checkbox is selected
        boolean anySelected = cBox_bm.isSelected() ||
                cBox_gl.isSelected() ||
                cBox_gs.isSelected() ||
                cBox_mcs.isSelected() ||
                cBox_ies.isSelected() ||
                cBox_ec.isSelected();

        // Enable or disable the "Continue" button based on selection
        btn_cont.setDisable(!anySelected);

        // Update the label to reflect the state (optional)
        if (anySelected) {
            lb_fg.setText("You have selected your fitness goals.");
        } else {
            lb_fg.setText("Please select at least one goal.");
        }
    }
}



