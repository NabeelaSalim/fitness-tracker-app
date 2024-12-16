package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartWorkoutController implements Initializable {

    @FXML
    public Label lb_start;
    @FXML
    public Label lb_finish;
    @FXML
    public Button btn_back;
    @FXML
    public Button btn_st;
    @FXML
    public Button btn_finish;
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    lb_start.setVisible(false);
    lb_finish.setVisible(false);

    // Add event handler for btn_st
    btn_st.setOnAction(event -> {
        lb_start.setVisible(true);
        lb_finish.setVisible(false);
    });

    // Add event handler for btn_finish
    btn_finish.setOnAction(event -> {
        lb_start.setVisible(false);
        lb_finish.setVisible(true);

        // Here you can define the values to add
        int caloriesToAdd = 280; // Example value
        int workoutsToAdd = 1;    // Example value
        double distanceToAdd = 0.52; // Example value in kilometers

        // Update the goals in FitGoalsController
        FitGoalsController.updateGoals(caloriesToAdd, workoutsToAdd, distanceToAdd);

        // Pause for 2 seconds before navigating to the FitnessGoals.fxml
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            // Navigate to the FitnessGoals.fxml scene
            UserService userService = new UserService();
            userService.changeScence1(event, "fitGoals_view.fxml", "Your Fitness Goals");
        });
        pause.play();
    });

    btn_back.setOnAction(event -> {
        UserService userService = new UserService();
        userService.changeScence1(event, "select_view.fxml", "select an option");
    });
}
}

