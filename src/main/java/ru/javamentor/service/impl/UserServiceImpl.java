package ru.javamentor.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.service.RoleService;
import ru.javamentor.service.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(@Autowired PasswordEncoder passwordEncoder, @Autowired UserRepository userRepository, @Autowired RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);

        return savedUser;
    }

    @Override
    public void addUser(String name, String password, Integer age, String role) throws DBException {
        Long role_ID = 0L;
        role = role.toUpperCase();

        if (isExistsUser(name)) {
            System.out.println("This Username already exists, please choose another name!");
            throw new DBException(new Exception("This Username already exists, please choose another name!"));
        }

        if (role.contains("ADMIN")) {
            role = "ROLE_ADMIN";
            role_ID = roleService.getRoleIdByName(role);
        }

        if (role.contains("USER")) {
            role = "ROLE_USER";
            role_ID = roleService.getRoleIdByName(role);
        }

        addUser(new User(name, passwordEncoder.encode(password), age, role, role_ID));
        roleService.addRoles(getUserIdByName(name), role_ID);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUsername(name).get();
    }

    @Override
    public User getUserByID(Long ID) {
        return userRepository.findById(ID).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) throws DBException {
        if (isExistsUser(user.getUsername())) {
            System.out.println("This Username already exists, please choose another name!");
            throw new DBException(new Exception("This Username already exists, please choose another name!"));
        }

        if (StringUtils.isEmpty(user.getUsername())) {
            user.setUsername(getUserByID(user.getId()).getUsername());
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(getUserByID(user.getId()).getPassword());
        } else {
            if (!user.getPassword().contains("$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        if (ObjectUtils.isEmpty(user.getAge())) {
            user.setAge(getUserByID(user.getId()).getAge());
        }

        if (StringUtils.isEmpty(user.getRole())) {
            user.setRole(getUserByID(user.getId()).getRole());
        } else {
            user.setRole(user.getRole().toUpperCase());

            if (user.getRole().contains("ADMIN")) {
                user.setRole("ROLE_ADMIN");
                user.setRole_id(roleService.getRoleIdByName(user.getRole()));
            }

            if (user.getRole().contains("USER")) {
                user.setRole("ROLE_USER");
                user.setRole_id(roleService.getRoleIdByName(user.getRole()));
            }
        }

        userRepository.saveAndFlush(user);
        roleService.addRoles(user.getId(), user.getRole_id());
    }

    @Override
    public Boolean isExistsUser(String name) {
        try {
            return getUserByName(name) != null;
        } catch (NoSuchElementException e) {
            e.getMessage();

            return false;
        }
    }

    @Override
    public void deleteUserByName(String name) {
        User userByName = getUserByName(name);
        userRepository.deleteById(userByName.getId());
    }

    @Override
    public Long getUserIdByName(String name) {
        return userRepository.findIdByUsername(name).get().getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);

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
