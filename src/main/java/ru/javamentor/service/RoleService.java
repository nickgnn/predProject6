package ru.javamentor.service;

import ru.javamentor.exception.DBException;

public interface RoleService {
    Long getRoleIdByName(String name) throws DBException;
}
