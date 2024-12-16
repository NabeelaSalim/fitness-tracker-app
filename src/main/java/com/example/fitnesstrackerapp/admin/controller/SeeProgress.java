package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;



public class SeeProgress implements Initializable {
    @FXML
    private BarChart<String, Number> progressBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btn_back;
    SeeProgress instance;

    public static int totalCalories = 0;
    public static int totalWorkouts = 0;
    public static double totalDistance = 0.0;
    public void initialize(URL url, ResourceBundle resourceBundle) {instance = this; // Assign the static instance for external access

        // Initialize the BarChart with current progress data
        updateGraph();

        // Handle back button click
        btn_back.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event, "select_view.fxml", "Select an option");
        });
    }

    /**
     * Refresh the progress graph dynamically based on fitness goal data.
     */
    public void updateGraph() {
        // Clear existing data
        progressBarChart.getData().clear();

        // Create a new data series
        XYChart.Series<String, Number> progressSeries = new XYChart.Series<>();
        progressSeries.setName("Fitness Progress");

        // Add data points from FitGoalsController
        progressSeries.getData().add(new XYChart.Data<>("Calories", FitGoalsController.totalCalories));
        progressSeries.getData().add(new XYChart.Data<>("Workouts", FitGoalsController.totalWorkouts));
        progressSeries.getData().add(new XYChart.Data<>("Distance", FitGoalsController.totalDistance));

        // Add the series to the BarChart
        progressBarChart.getData().add(progressSeries);
    }

    /**
     * Static method to refresh the graph from other controllers.
     */
   // public static void refreshProgressGraph() {

       // if (instance != null) {
         //   instance.updateGraph();
       // }
    //}
}
