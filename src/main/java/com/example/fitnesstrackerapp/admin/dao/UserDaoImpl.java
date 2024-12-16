package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;
import com.example.fitnesstrackerapp.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }
    @Override
    public User addUser(UserDto userDto) {
        try {
            String query = "INSERT INTO fj_login (username, email, password, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userDto.getName());
            stmt.setString(2, userDto.getEmail());
            stmt.setString(3, userDto.getPassword());
            stmt.setString(4, userDto.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User();
        user = getUserByEmail(userDto.getEmail());
        return user;

    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM fj_login WHERE email = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
