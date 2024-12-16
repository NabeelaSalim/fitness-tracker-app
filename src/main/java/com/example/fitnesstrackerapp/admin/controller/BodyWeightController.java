package com.example.fitnesstrackerapp.admin.controller;


import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class BodyWeightController implements Initializable {
    @FXML
    private Button btn_bodyweightstartworkout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
       btn_bodyweightstartworkout.setOnAction(event -> {
           UserService userService = new UserService();
           userService.changeScence1(event,"StartWorkout.fxml", "Bodyweight Start Workout");
       });

    }


}
