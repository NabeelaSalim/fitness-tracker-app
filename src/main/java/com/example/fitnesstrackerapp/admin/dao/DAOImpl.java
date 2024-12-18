package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAOInterface {
    private static final String RESOURCES_FOLDER = "src/main/resources/appdata/";
    private List<User> UserInformation = new ArrayList<>();

    private Path getResourcesPath(String filename) {
        return Paths.get(RESOURCES_FOLDER + filename);
    }


    @Override
    public List addObject(List object) {
        User user = new User();
        List<User> users = new ArrayList<>();

        user.setName("rayhan");
        user.setUserId(3L);
        user.setEmail("rayhan@gmail.com");
        user.setPassword("0000");
        users.add(user);


        try {
            // Create the path for the text file in the resources folder
            Path filePath = getResourcesPath("usersDetails" + ".txt");

            // Write user data to the file
            Files.writeString(filePath, user.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return users;
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public String add(List object) {
        return "";
    }

    @Override
    public Object getObj(int id) {
        return null;
    }


}
