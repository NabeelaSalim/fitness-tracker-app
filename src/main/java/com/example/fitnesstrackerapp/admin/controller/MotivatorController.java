package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MotivatorController implements Initializable {

    @FXML
   public Label lb_mot;
    @FXML
   public Button btn_continue;
    @FXML
    private CheckBox cBox_loseWeight;
    @FXML
    private CheckBox cBox_active;
    @FXML
    private CheckBox cBox_bStrength;
    @FXML
    private CheckBox cBox_recharge;
    @FXML
    private CheckBox cBox_learnBasics;
    @FXML
    private CheckBox cBox_health;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_continue.setDisable(true);

        // Setting checkboxes to action
        cBox_loseWeight.setOnAction(event -> handleCheckBoxAction(cBox_loseWeight));
        cBox_active.setOnAction(event -> handleCheckBoxAction(cBox_active));
        cBox_bStrength.setOnAction(event -> handleCheckBoxAction(cBox_bStrength));
        cBox_recharge.setOnAction(event -> handleCheckBoxAction(cBox_recharge));
        cBox_learnBasics.setOnAction(event -> handleCheckBoxAction(cBox_learnBasics));
        cBox_health.setOnAction(event -> handleCheckBoxAction(cBox_health));



            btn_continue.setOnAction(event -> {
                UserService userService = new UserService();
                userService.changeScence1(event,"priorities_view.fxml", "Select your top priorities");
            });

    }

    private void handleCheckBoxAction(CheckBox checkBox) {
        boolean anySelected = cBox_loseWeight.isSelected() ||
                cBox_active.isSelected() ||
                cBox_bStrength.isSelected() ||
                cBox_recharge.isSelected() ||
                cBox_learnBasics.isSelected() ||
                cBox_health.isSelected();

        // Enable or disable the "Continue" button based on selection
        btn_continue.setDisable(!anySelected);
        if (anySelected) {
            lb_mot.setText("You have selected an option.");
        } else {
            lb_mot.setText("Please select at least one option.");
        }
    }




    }
