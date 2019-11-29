package ru.javamentor.service;

import ru.javamentor.exception.DBException;

public interface RoleService {
    Long getRoleIdByName(String name) throws DBException;

    void addRoles(Long user_id, Long role_id) throws DBException;
}
