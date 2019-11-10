package ru.javamentor.service;

import ru.javamentor.exception.DBException;
import org.springframework.stereotype.Component;
import ru.javamentor.model.User;

import java.util.List;

@Component("userService")
public interface Service {
    void createTable() throws DBException;

    void cleanUp() throws DBException;

    List<User> getAllUsers() throws DBException;

    void addUser(String name, int age) throws DBException;

    void addUser(String name, int age, String password, String role) throws DBException;

    User getUserByName(String name) throws DBException;

    void updateUser(User user, String name, int age, String password, String role) throws DBException;

    boolean isExistsUser(String name) throws DBException;

    void deleteUserByName(String name) throws DBException;

    void deleteUserById(Long id) throws DBException;

    long getUserIdByName(String name) throws DBException;
}
