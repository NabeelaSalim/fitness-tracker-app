module com.example.fitnesstrackerapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires spring.web;
    requires java.desktop;


    //opens com.example.fitnesstrackerapp to javafx.fxml;
    opens com.example.fitnesstrackerapp to javafx.fxml;
    opens com.example.fitnesstrackerapp.admin.controller to javafx.fxml;
    exports com.example.fitnesstrackerapp;
    exports com.example.fitnesstrackerapp.admin.controller;
}