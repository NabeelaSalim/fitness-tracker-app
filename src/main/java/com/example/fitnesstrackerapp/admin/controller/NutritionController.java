package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;



public class NutritionController implements Initializable {

    @FXML
    public Label lb_nutrition;
    @FXML
    public Label menuBreakfast;
    @FXML
    public Label menuLunch;
    @FXML
    public Label menuDinner;
    @FXML
    public StackPane sp;
    @FXML
    public Button btn_back;
    @FXML
    private Button btnBreakfast;

    @FXML
    private Button btnLunch;

    @FXML
    private Button btnDinner;

   public void initialize(URL url, ResourceBundle resourceBundle) {
       // Ensure all labels are hidden initially
       menuBreakfast.setVisible(false);
       menuLunch.setVisible(false);
       menuDinner.setVisible(false);

       // Button actions
       btnBreakfast.setOnAction(e -> showMenu("breakfast"));
       btnLunch.setOnAction(e -> showMenu("lunch"));
       btnDinner.setOnAction(e -> showMenu("dinner"));

       btn_back.setOnAction(event -> {
           UserService userService = new UserService();
           userService.changeScence1(event, "select_view.fxml", "select an option");
       });
   }

    private void showMenu(String menu) {
        // Hide all menus initially
        menuBreakfast.setVisible(false);
        menuLunch.setVisible(false);
        menuDinner.setVisible(false);

        // Show the selected menu
        switch (menu) {
            case "breakfast":
                menuBreakfast.setVisible(true);
                break;
            case "lunch":
                menuLunch.setVisible(true);
                break;
            case "dinner":
                menuDinner.setVisible(true);
                break;
        }
    }}
