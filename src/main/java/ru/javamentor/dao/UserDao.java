package ru.javamentor.dao;

import org.springframework.stereotype.Component;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public interface UserDao {
    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void addUser(String name, String password, Integer age, String role, Long role_ID) throws SQLException;

    User getUserByName(String name) throws SQLException;

    User getUserById(Long ID) throws SQLException;

    void updateUser(User user) throws SQLException;

    Long getUserIdByName(String name) throws SQLException;

    Boolean isExistsUser(String name) throws SQLException;

    void deleteUserByName(String name) throws SQLException;

    void deleteUserById(Long id) throws SQLException;
}
