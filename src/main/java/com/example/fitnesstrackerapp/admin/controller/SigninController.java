package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.dto.UserRegistrationResponseDto;
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

public class SigninController implements Initializable {
    @FXML
    private Button btn_create;
    @FXML
    private Button btn_redirect_login;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_confirm_password;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_phone;
    @FXML
    public Label lbl_work;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tf_username.getText().isEmpty() && tf_phone.getText().isEmpty() &&
                        tf_email.getText().isEmpty() && tf_password.getText().isEmpty() &&
                        tf_confirm_password.getText().isEmpty()) {
                    lbl_work.setText("Details Incomplete! Please fill in all details");
                    return;
                }

                // Initialize an empty StringBuilder to collect missing fields
                StringBuilder missingFields = new StringBuilder("Details Incomplete! Please fill in");

                // Check each field and add its name to the message if it's empty
                boolean isIncomplete = false;
                if (tf_username.getText().isEmpty()) {
                    missingFields.append(" user name");
                    isIncomplete = true;
                }
                if (tf_phone.getText().isEmpty()) {
                    if (isIncomplete) missingFields.append(" and"); // Add "and" if there are multiple missing fields
                    missingFields.append(" phone number");
                    isIncomplete = true;
                }
                if (tf_email.getText().isEmpty()) {
                    if (isIncomplete) missingFields.append(" and");
                    missingFields.append(" email");
                    isIncomplete = true;
                }
                if (tf_password.getText().isEmpty()) {
                    if (isIncomplete) missingFields.append(" and");
                    missingFields.append(" password");
                    isIncomplete = true;
                }
                if (tf_confirm_password.getText().isEmpty()) {
                    if (isIncomplete) missingFields.append(" and");
                    missingFields.append(" confirm password");
                    isIncomplete = true;
                }

                // If any specific fields are missing, set the label to the constructed message
                if (isIncomplete) {
                    lbl_work.setText(missingFields.toString());
                    return;
                }
                /*if (tf_email.getText().isEmpty() || tf_password.getText().isEmpty() ||
                        tf_confirm_password.getText().isEmpty() || tf_username.getText().isEmpty() ||
                        tf_phone.getText().isEmpty()) {
                    lbl_work.setText("Details Incomplete! Please fill in all details");
                    return;
                }*/
                // Validate specific fields
                if (!validateName(tf_username.getText())) {
                    lbl_work.setText("Invalid Name! Name must contain only letters.");
                    return;
                }

                if (tf_phone.getText().isEmpty()) {
                    lbl_work.setText("Phone Number cannot be empty!");
                    return;
                }

                String phoneValidationResponse = validatePhoneNumber(tf_phone.getText());
                if (phoneValidationResponse != null) {
                    lbl_work.setText(phoneValidationResponse);
                    return;
                }



                if (!validateEmail(tf_email.getText())) {
                    lbl_work.setText("Invalid Email! Please enter a valid email address.");
                    return;
                }

                UserService userService = new UserService();
                String password = PasswordHashing.hashPassword(tf_password.getText());
                String confirm_password = PasswordHashing.hashPassword(tf_confirm_password.getText());

                //after validate login create user
                UserDto userDto = new UserDto();
                userDto.setEmail(tf_email.getText());
                userDto.setPassword(password);
                userDto.setName(tf_username.getText());
                userDto.setPhone(tf_phone.getText());

                //User createdUser = userService.addUser(event, userDto, lbl_work, confirm_password);
                UserRegistrationResponseDto responseDto = userService.userSigning(event, userDto, lbl_work, confirm_password);
                if (responseDto != null && responseDto.getUser() != null) {
                    userService.changeScence1(event, "user-login.fxml", "You can now Login to your account");
                } else {
                    lbl_work.setText(responseDto.getResponseMessage());
                }

            }
        });
        btn_redirect_login.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event, "existing-login.fxml", "Login to your account");
        });

    }

    // Helper method to validate the name
    private boolean validateName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    // Helper method to validate the phone number
    private String validatePhoneNumber(String phoneNumber) {
        // Check if the phone number contains only digits
        if (!phoneNumber.matches("\\d*")) {
            if (phoneNumber.length() != 10) {
                return "Invalid Phone Number! It must contain exactly 10 digits and no letters.";
            } else {
                return "Invalid Phone Number! It must contain no letters.";
            }
        }

        // Check if the phone number is exactly 10 digits long
        if (phoneNumber.length() != 10) {
            return "Invalid Phone Number! It must contain 10 digits.";
        }

        // If both checks pass
        return null; // Indicates valid phone number
    }



    // Helper method to validate the email
    private boolean validateEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.com$");
    }

    public void setUserInformation(String username) {
        lbl_work.setText("Welcome " + username);
    }

}
