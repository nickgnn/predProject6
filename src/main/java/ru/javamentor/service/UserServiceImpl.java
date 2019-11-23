package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void addUser(String name, String password, Integer age, String role) throws DBException {
        try {
            Long role_ID = 0L;
            role = role.toUpperCase();

            if (role.contains("ADMIN")) {
                role = "ROLE_ADMIN";
                role_ID = userDao.getRoleIdByName(role);
            }

            if (role.contains("USER")) {
                role = "ROLE_USER";
                role_ID = userDao.getRoleIdByName(role);
            }

            userDao.addUser(name, encoder.encode(password), age, role, role_ID);
            userDao.addRoles(userDao.getUserIdByName(name), role_ID);
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
    public Long getUserIdByName(String name) throws DBException {
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

            if (!StringUtils.isEmpty(newUser.getRole())) {
                user.setRole(newUser.getRole());

                user.setRole(user.getRole().toUpperCase());

                if (user.getRole().contains("ADMIN")) {
                    user.setRole("ROLE_ADMIN");
                    user.setRole_id(userDao.getRoleIdByName(user.getRole()));
                }

                if (user.getRole().contains("USER")) {
                    user.setRole("ROLE_USER");
                    user.setRole_id(userDao.getRoleIdByName(user.getRole()));
                }
            }

            user.setAge(newUser.getAge());

            userDao.updateUser(user);
            userDao.addRoles(user.getId(), user.getRole_id());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Boolean isExistsUser(String name) throws DBException {
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
