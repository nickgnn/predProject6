package ru.javamentor.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers() throws DBException;

    void addUser(String name, String password, Integer age, String role) throws DBException;

    User addUser(User user) throws DBException;

    User getUserByName(String name) throws DBException;

    User getUserByID(Long ID) throws DBException;

    void updateUser(User user) throws DBException;

    Boolean isExistsUser(String name) throws DBException;

    void deleteUserByName(String name) throws DBException;

    void deleteUserById(Long id) throws DBException;

    Long getUserIdByName(String name) throws DBException;
}
