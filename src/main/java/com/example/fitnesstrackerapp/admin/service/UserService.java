package com.example.fitnesstrackerapp.admin.service;

import com.example.fitnesstrackerapp.FJTracker;
import com.example.fitnesstrackerapp.admin.dao.DAOImpl;
import com.example.fitnesstrackerapp.admin.formcontroller.SettingsFormController;
import com.example.fitnesstrackerapp.admin.formcontroller.SigninFormController;
import com.example.fitnesstrackerapp.admin.formcontroller.VitalsFormController;
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
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static void validateLogin(ActionEvent event, String email, String password, Label lblgetstarted) {

        try {
            DAOImpl dao = new DAOImpl();
            User user = dao.getObjbyEmail(email);


            if (user == null) {
                lblgetstarted.setText("User with email does not exist");
            } else {
                String hashedpassword = PasswordHashing.hashPassword(password);
                if (user.getPassword().trim().equals(hashedpassword)) {
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

    public static void validateExistingLogin(ActionEvent event, String email, String password, Label lblgetstarted, Long userID) {
        try {
            DAOImpl dao = new DAOImpl();

            User user = dao.getObjbyEmail(email);

            if (user == null) {
                lblgetstarted.setText("User with email does not exist");
            }
            if (user != null) {
                String hashedpassword = PasswordHashing.hashPassword(password);
                if (user.getPassword().equals(hashedpassword)) {
                    UserVital vital = dao.getObjVital(userID);
                    if (vital == null) {
                        changeScenceEmail(event, "user_vitals.fxml", "Welcome, " + email, email);
                    } else {
                        changeScenceEmail(event, "app-settings.fxml", "Welcome, " + email, email);
                        //changeScence1(event, "select_view.fxml", "Welcome, " + email);
                    }

                } else {
                    lblgetstarted.setText("Wrong password try again!");
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            exception.getCause();
        }
    }

    public static List<User> addUser(ActionEvent event, UserDto userDto, Label lbl_work, String confirm) {
        List<User> users = new ArrayList<>();
        if (confirm != null && confirm.equals(userDto.getPassword())) {
            DAOImpl daoImpl = new DAOImpl();

            // User createduser = userDao.addUser(userDto);
            users = daoImpl.addObject(userDto, "userType");
            if (users != null) {
                lbl_work.setText("User added successfully");
            } else {
                lbl_work.setText("User not added successfully");
            }
            return users;
        } else {

            lbl_work.setText("Miss Match password");
        }

        return users;
    }


    public static UserRegistrationResponseDto userSigning(ActionEvent event, UserDto userDto, Label lbl_work, String confirm) {
        String responseMessage;
        List<User> users = new ArrayList<>();

        if (confirm != null && confirm.equals(userDto.getPassword())) {
            DAOImpl daoImpl = new DAOImpl();

            users = daoImpl.addObject(userDto, "userType");

            if (users != null) {
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

        /*UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(userDto.getEmail());*/
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        responseDto.setResponseMessage(responseMessage);
        responseDto.setUsers(users);
        return responseDto;
    }

    public static UserVital addUserVitals(ActionEvent event, UserVitalDto userVitalDto, Label lbl_added) {
        List<UserVital> userVitals = new ArrayList<>();
        DAOImpl daoImpl = new DAOImpl();
        userVitals = daoImpl.addObject(userVitalDto, "userVitalType");

        if (userVitals != null) {
            lbl_added.setText("Vitals added successfully");
            changeScenceEmail(event, "app-settings.fxml", "Welcome to app settings", userVitalDto.getEmail());
            //changeScence1(event, "app_settings.fxml", "Whats your go to motivator?");
        } else {
            lbl_added.setText("User Does not Exist");
        }
        return (UserVital) userVitals;
    }

    public static User updateUser(ActionEvent event, UserDto userDto, Label lbl_updated, String confirm) {
        List<User> users = new ArrayList<>();
        DAOImpl daoImpl = new DAOImpl();

        // Check if a password change is being attempted
        if ((userDto.getPassword() != null && !userDto.getPassword().isEmpty()) ||
                (confirm != null && !confirm.isEmpty())) {

            // Validate password confirmation
            if (confirm != null && confirm.equals(userDto.getPassword())) {
                users = daoImpl.updateObjectListToJsonFile(userDto, "UserType");
                if (users != null) {
                    lbl_updated.setText("User added successfully");
                } else {
                    lbl_updated.setText("User not added successfully");
                }
            } else {
                lbl_updated.setText("Miss Match password");
                return null;
            }
        } else {
            // Handle case when only name is being changed
            users = daoImpl.updateObjectListToJsonFile(userDto, "UserType");
            if (users != null) {
                lbl_updated.setText("User added successfully");
            } else {
                lbl_updated.setText("User not added successfully");
            }
        }
        return (User) users;
    }



    public static void changeScence(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(FJTracker.class.getResource(fxmlFile));
                root = loader.load();

                SigninFormController signinController = loader.getController();
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

            Object controller = loader.getController();

            // Check if the controller has a setUserInformation method and invoke it
            if (controller instanceof VitalsFormController) {
                ((VitalsFormController) controller).setUserInformation(email);
            } else if (controller instanceof SettingsFormController) {
                ((SettingsFormController) controller).setUserInformation(email);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void viewProfile(ActionEvent event) {
    }
}




