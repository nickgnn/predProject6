package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
    public void addUser(String name, String password) throws DBException {
        try {
            userDao.addUser(name, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(String name, String password, Integer age, String role) throws DBException {
        try {
            userDao.addUser(name, password, age, role);
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
            if (!StringUtils.isEmpty(newUser.getUsername())) {
                user.setUsername(newUser.getUsername());
            }

            if (!StringUtils.isEmpty(newUser.getPassword())) {
                user.setPassword(newUser.getPassword());
            }

            if (!StringUtils.isEmpty(newUser.getAge())) {
                user.setAge(newUser.getAge());
            }

            if (!StringUtils.isEmpty(newUser.getRole())) {
                user.setRole(newUser.getRole());
            }

            if (!StringUtils.isEmpty(newUser.getRole_id())) {
                user.setRole_id(newUser.getRole_id());
            }

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
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.getUserByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
