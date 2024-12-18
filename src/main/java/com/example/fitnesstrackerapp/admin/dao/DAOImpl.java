package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAOInterface {
    private static final String RESOURCES_FOLDER = "src/main/resources/appdata/";

    private Path getResourcesPath(String filename) {
        return Paths.get(RESOURCES_FOLDER + filename);
    }


    @Override
    public List addObject(List object) {
        User user = new User();
        List<User> users = new ArrayList<>();

        user.setName("Rayhan Rizwan");
        user.setUserId(1L);
        users.add(user);


        try {
            // Create the path for the text file in the resources folder
            Path filePath = getResourcesPath("user_" + user.getUserId() + ".txt");

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
