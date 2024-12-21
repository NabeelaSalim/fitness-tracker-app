package com.example.fitnesstrackerapp.admin.formcontroller;

import com.example.fitnesstrackerapp.admin.controller.SettingsController;
import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.service.UserService;
import com.example.fitnesstrackerapp.utils.PasswordHashing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsFormController implements Initializable {
    @FXML
    private TextField tf_changename;
    @FXML
    private TextField tf_changepass;
    @FXML
    private TextField tf_changeconfirmpass;
    @FXML
    private Button btn_submitchanges;
    @FXML
    private Button btn_returnprofile;
    @FXML
    private Label lbl_confirmchanges;
    @FXML
    public Label lbl_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_submitchanges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String password = tf_changepass.getText().trim();
                String confirmPassword = tf_changeconfirmpass.getText().trim();
                String newName = tf_changename.getText().trim();

                if (newName.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                    lbl_confirmchanges.setText("No changes were made.");
                    return;
                }

                if (!newName.isEmpty() && !newName.matches("[a-zA-Z ]+")) {
                    lbl_confirmchanges.setText("Invalid Name! Only letters and spaces are allowed.");
                    return;
                }

// If both password fields are empty, skip password validation
                if (password.isEmpty() && confirmPassword.isEmpty()) {
                    // Only change the name
                    UserDto userDto = new UserDto();
                    userDto.setEmail(lbl_email.getText()); // Ensure email is included
                    if (!newName.isEmpty()) {
                        userDto.setName(newName);
                    }

                    // Pass the validated data to SettingsController
                    SettingsController settingsController = new SettingsController();
                    settingsController.updatdeUser(userDto, event, lbl_confirmchanges, null);

                    lbl_confirmchanges.setText("Changes validated and submitted successfully!");
                    return;
                }

// If one of the password fields is empty
                if (password.isEmpty() || confirmPassword.isEmpty()) {
                    lbl_confirmchanges.setText("Please fill out both password fields.");
                    return;
                }

// If both password fields are filled, validate them
                if (!password.equals(confirmPassword)) {
                    lbl_confirmchanges.setText("Passwords do not match. Please try again.");
                    return;
                }

// If everything is valid, create the UserDto and submit changes
                UserDto userDto = new UserDto();
                userDto.setEmail(lbl_email.getText());
                if (!newName.isEmpty()) {
                    userDto.setName(newName);
                }
                userDto.setPassword(PasswordHashing.hashPassword(password));

                SettingsController settingsController = new SettingsController();
                settingsController.updatdeUser(userDto, event, lbl_confirmchanges, PasswordHashing.hashPassword(confirmPassword));

                lbl_confirmchanges.setText("Changes validated and submitted successfully!");


            }
        });
        btn_returnprofile.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event, "profile-view.fxml", "User Profile");
        });


    }

    public void setUserInformation(String email) {
        lbl_email.setText(email);

    }

}
