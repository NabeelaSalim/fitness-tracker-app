package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectController implements Initializable {
    @FXML
    public Button btn_workSched;
    @FXML
    public Button btn_progress;
    @FXML
    public Button btn_nutrition;
    @FXML
    public Button btn_fitGoals;
    @FXML
    public Button btn_calendar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_workSched.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"workoutSchedule_view.fxml", "Select your fitness level");
        });
        btn_progress.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"ProgressVisualization.fxml", "Select your fitness level");
        });


        btn_nutrition.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"nutrition_view.fxml", "Select your fitness level");
        });
        btn_fitGoals.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"fitGoals_view.fxml", "Select your fitness level");
        });
        btn_calendar.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"calendar_view.fxml", "Select your fitness level");
        });



    }
}
