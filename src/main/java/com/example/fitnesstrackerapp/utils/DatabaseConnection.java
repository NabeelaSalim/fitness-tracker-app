package com.example.fitnesstrackerapp.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String dbName = "fit_journey";
        String dbUser = "root";
        String dbPassword = "Rayhan@8448";
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    public static void validateLogin(ActionEvent event, String email, String password, Label lblgetstarted) {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectionDB = connection.getConnection();
        String verifyLogin = "SELECT count(1) FROM fj_login WHERE email = '" + email + "' AND password = '" + password + "'";
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    lblgetstarted.setText("Welcome to JavaFX Application!");
                } else {
                    lblgetstarted.setText("wrong password try again");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }
}
