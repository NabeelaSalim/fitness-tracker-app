package com.example.fitnesstrackerapp;

import com.example.fitnesstrackerapp.admin.controller.FitGoalsController;
import com.example.fitnesstrackerapp.admin.controller.SeeProgress;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("motivator_view.fxml"));
        //FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("priorities_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
       //Scene scene1 = new Scene(fxmlLoader1.load(), 800, 500);

        stage.setTitle("Hello!");
        stage.setScene(scene);
       // stage.setScene(scene1);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}