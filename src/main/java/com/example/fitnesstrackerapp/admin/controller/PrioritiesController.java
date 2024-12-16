package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PrioritiesController implements Initializable {

    @FXML
    public Label lb_prio;
    @FXML
    public Button btn_cont;
    @FXML
    private CheckBox cBox_arms;
    @FXML
    private CheckBox cBox_body;
    @FXML
    private CheckBox cBox_Abs;
    @FXML
    private CheckBox cBox_Core;
    @FXML
    private CheckBox cBox_cardio;
    @FXML
    private CheckBox cBox_flexMob;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_cont.setDisable(true);

        cBox_arms.setOnAction(event -> handleCheckBoxClick(cBox_arms));
        cBox_body.setOnAction(event -> handleCheckBoxClick(cBox_body));
        cBox_Abs.setOnAction(event -> handleCheckBoxClick(cBox_Abs));
        cBox_Core.setOnAction(event -> handleCheckBoxClick(cBox_Core));
        cBox_cardio.setOnAction(event -> handleCheckBoxClick(cBox_cardio));
        cBox_flexMob.setOnAction(event -> handleCheckBoxClick(cBox_flexMob));


        btn_cont.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"fitnessLevel_view.fxml", "Select your fitness level");
        });


    }

    private void handleCheckBoxClick(CheckBox checkBox) {
        boolean anySelected = cBox_arms.isSelected() ||
                cBox_body.isSelected() ||
                cBox_Abs.isSelected() ||
                cBox_Core.isSelected() ||
                cBox_cardio.isSelected() ||
                cBox_flexMob.isSelected();

        // Enable or disable the "Continue" button based on selection
        btn_cont.setDisable(!anySelected);

        // Update the label to reflect the state (optional)
        if (anySelected) {
            lb_prio.setText("You have selected your priorities.");
        } else {
            lb_prio.setText("Please select at least one priority.");
        }
    }
}

