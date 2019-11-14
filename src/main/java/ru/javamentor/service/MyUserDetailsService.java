package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;

import java.util.Arrays;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;

        try {
            user = userService.getUserByName(username);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (user == null)
            throw new UsernameNotFoundException("Oops!");

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails
                .User(user.getName(), user.getPassword(), authorities);
    }
}
