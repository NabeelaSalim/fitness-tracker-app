package com.example.fitnesstrackerapp.admin.service;

import com.example.fitnesstrackerapp.FJTracker;
import com.example.fitnesstrackerapp.admin.controller.SigninController;
import com.example.fitnesstrackerapp.admin.controller.VitalsController;
import com.example.fitnesstrackerapp.admin.dao.UserDaoImpl;
import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.dto.UserRegistrationResponseDto;
import com.example.fitnesstrackerapp.admin.dto.UserVitalDto;
import com.example.fitnesstrackerapp.admin.model.User;
import com.example.fitnesstrackerapp.admin.model.UserVital;
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
                lblgetstarted.setText("User with email does not exist");
            } else {
                String hashedpassword = PasswordHashing.hashPassword(password);
                if (user.getPassword().equals(hashedpassword)) {
                    changeScenceEmail(event, "user_vitals.fxml", "Welcome, " + email, email);
                } else {
                    lblgetstarted.setText("Wrong password try again!");
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public static void validateExistingLogin(ActionEvent event, String email, String password, Label lblgetstarted) {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getUserByEmail(email);

            if (user == null) {
                lblgetstarted.setText("User with email does not exist");
            }
            if (user != null) {
                String hashedpassword = PasswordHashing.hashPassword(password);
                if (user.getPassword().equals(hashedpassword)) {
                    UserVital createdvitals = userDao.getUserVitalsById(user.getUserId());
                    if (createdvitals == null) {
                        changeScenceEmail(event, "user_vitals.fxml", "Welcome, " + email, email);
                    }else{
                        changeScence1(event, "select_view.fxml", "Welcome, " + email);
                    }

                } else {
                    lblgetstarted.setText("Wrong password try again!");
                }

            }
        }catch (Exception exception) {
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
        UserDaoImpl userDao = new UserDaoImpl();

        User user = userDao.getUserByEmail(userDto.getEmail());
        return user;
    }

    public static UserRegistrationResponseDto userSigning(ActionEvent event, UserDto userDto, Label lbl_work, String confirm) {
        String responseMessage;

        if (confirm != null && confirm.equals(userDto.getPassword())) {
            UserDaoImpl userDao = new UserDaoImpl();

            User createduser = userDao.addUser(userDto);

            if (createduser != null) {
                responseMessage = "User added successfully";
                //lbl_work.setText("User added successfully");
            } else {
                responseMessage = "User not added successfully";
                // lbl_work.setText("User not added successfully");
            }

        } else {

            responseMessage = "Miss Match password";
            //lbl_work.setText("Miss Match password");
        }

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(userDto.getEmail());
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        responseDto.setResponseMessage(responseMessage);
        responseDto.setUser(user);
        return responseDto;
    }

    public static UserVital addUserVitals(ActionEvent event, UserVitalDto userVitalDto, Label lbl_added) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserVital createdvitals = userDao.addUserVitals(userVitalDto);
        if (createdvitals != null) {
            lbl_added.setText("Vitals added successfully");
        } else {
            lbl_added.setText("Vitals not added successfully");
        }
        return createdvitals;
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
        stage.setScene(new Scene(root, 800, 600));
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
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void changeScenceEmail(ActionEvent event, String fxmlFile, String title, String email) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(FJTracker.class.getResource(fxmlFile));
            root = loader.load();

            VitalsController vitalsController = loader.getController();
            vitalsController.setUserInformation(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            root = FXMLLoader.load(FJTracker.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void viewProfile(ActionEvent event) {
    }
}




