package com.example.fitnesstrackerapp.admin.service;

import com.example.fitnesstrackerapp.FJTracker;
import com.example.fitnesstrackerapp.admin.controller.SigninController;
import com.example.fitnesstrackerapp.admin.dao.UserDaoImpl;
import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;
import com.example.fitnesstrackerapp.utils.PasswordHashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserService {
    public static void validateLogin(ActionEvent event, String email, String password, Label lblgetstarted) {

        try {
            UserDaoImpl userDao = new UserDaoImpl();

            User user = userDao.getUserByEmail(email);

            if (user == null) {
                lblgetstarted.setText("User with email don't exist");
            } else {
                String hashedpassword = PasswordHashing.hashPassword(password);
                if (user.getPassword().equals(hashedpassword)) {
                    changeScence(event, "user_age.fxml", "Welcome: " + email, email);
                } else {
                    lblgetstarted.setText("Wrong password try again!");
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public static User addUser(ActionEvent event, UserDto userDto, Label lbl_work, String confirm) {
        if (confirm != null && confirm.equals(userDto.getPassword())) {
            UserDaoImpl userDao = new UserDaoImpl();

            User createduser = userDao.addUser(userDto);

            if (createduser != null) {
                lbl_work.setText("User added successfully");
            } else {
                lbl_work.setText("User not added successfully");
            }
            return createduser;
        } else {

            lbl_work.setText("Miss Match password");
        }
        return null;
    }

    public static void changeScence(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(FJTracker.class.getResource(fxmlFile));
                root = loader.load();

                SigninController signinController = loader.getController();
                signinController.setUserInformation(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(FJTracker.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    public static void changeScence1(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;
        try {
            root = FXMLLoader.load(FJTracker.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
}




