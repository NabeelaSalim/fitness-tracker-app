package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CalendarView implements Initializable {

    @FXML
    public Label lb_calendar;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btn_skipped;
    @FXML
    private Button btn_did;
    @FXML
   public Button btn_back;


    // Map to store date statuses ("did" or "skipped")
    private final Map<LocalDate, String> dateStatusMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Customize DatePicker cells to reflect status
        datePicker.setDayCellFactory(createDayCellFactory());

        // Set button actions
        btn_did.setOnAction(event -> markDate("did"));
        btn_skipped.setOnAction(event -> markDate("skipped"));


        btn_back.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"select_view.fxml", "select an option");


        });
        }

    private void markDate(String status) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            showAlert("No Date Selected", "Please select a date first.", Alert.AlertType.WARNING);
            return;
        }

        dateStatusMap.put(selectedDate, status);
        datePicker.setDayCellFactory(createDayCellFactory()); // Refresh DatePicker cells
    }

    private Callback<DatePicker, DateCell> createDayCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty && item != null) {
                    if (dateStatusMap.containsKey(item)) {
                        String status = dateStatusMap.get(item);
                        setStyle(status.equals("did") ? "-fx-background-color: #90ee90;" : "-fx-background-color: #ffcccb;");
                        setTooltip(new Tooltip(status.equals("did") ? "Completed" : "Skipped"));
                    }
                }
            }
        };
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}





