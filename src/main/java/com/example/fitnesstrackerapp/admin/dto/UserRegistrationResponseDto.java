package com.example.fitnesstrackerapp.admin.dto;


import com.example.fitnesstrackerapp.admin.model.User;

public class UserRegistrationResponseDto {

    User user;

    String responseMessage;

    public UserRegistrationResponseDto() {

    }

    public UserRegistrationResponseDto(User user, String responseMessage) {
        this.user = user;
        this.responseMessage = responseMessage;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


}
