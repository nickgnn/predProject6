package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javamentor.dao.UserDao;
import ru.javamentor.exception.DBException;
import org.springframework.stereotype.Component;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserService implements Service {
    @Autowired
    @Qualifier("userDaoByHibernate")
    UserDao userDao;

    @Override
    public void addUser(String name, int age) throws DBException {
        try {
            userDao.addUser(name, age);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(String name, int age, String password, String role) throws DBException {
        try {
            userDao.addUser(name, age, password, role);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User getUserByName(String name) throws DBException {
        try {
            return userDao.getUserByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public long getUserIdByName(String name) throws DBException {
        try {
            return userDao.getUserIdByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user, String name, int age, String password, String role) throws DBException {
        try {
            userDao.updateUser(user, name, age, password, role);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean isExistsUser(String name) throws DBException {
        try {
            return userDao.isExistsUser(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserByName(String name) throws DBException {
        try {
            userDao.deleteUserByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById(Long id) throws DBException {
        try {
            userDao.deleteUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void createTable() throws DBException {
        try {
            userDao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void cleanUp() throws DBException {
        try {
            userDao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
