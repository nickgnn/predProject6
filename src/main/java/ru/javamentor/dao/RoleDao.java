package ru.javamentor.dao;

import java.sql.SQLException;

public interface RoleDao {
    Long getRoleIdByName(String name) throws SQLException;

    void addRoles(Long user_id, Long role_id) throws SQLException;
}
