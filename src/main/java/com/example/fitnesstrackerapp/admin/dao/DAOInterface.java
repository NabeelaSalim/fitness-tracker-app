package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;

import java.util.List;

public interface DAOInterface<T> {


    List<T> addObject(List<T> object);

    List<T> getAll();

    String add(List<T> object);

    T getObj(int id);
}
