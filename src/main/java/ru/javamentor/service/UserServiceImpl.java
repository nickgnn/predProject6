package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.dao.UserDao;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
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
    public void updateUser(User user, User newUser) throws DBException {
        try {
            if (!newUser.getUsername().equals("")) {
                user.setUsername(newUser.getUsername());
            }

            if (!newUser.getPassword().equals("")) {
                user.setPassword(newUser.getPassword());
            }

            if (!newUser.getRole().equals("")) {
                user.setRole(newUser.getRole());
            }

            user.setAge(newUser.getAge());

            userDao.updateUser(user);
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.getUserByName(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
