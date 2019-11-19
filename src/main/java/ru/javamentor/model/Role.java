package ru.javamentor.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class Role implements GrantedAuthority {
    private Long id;
    private String name;
    private List<User> user;

    @Override
    public String getAuthority() {
        return null;
    }
}
