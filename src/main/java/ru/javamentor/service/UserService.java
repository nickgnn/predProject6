package ru.javamentor.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createTable() throws DBException;

    void cleanUp() throws DBException;

    List<User> getAllUsers() throws DBException;

    void addUser(String name, String password, Integer age, String role) throws DBException;

    User getUserByName(String name) throws DBException;

    void updateUser(User user) throws DBException;

    Boolean isExistsUser(String name) throws DBException;

    void deleteUserByName(String name) throws DBException;

    void deleteUserById(Long id) throws DBException;

    Long getUserIdByName(String name) throws DBException;
}
