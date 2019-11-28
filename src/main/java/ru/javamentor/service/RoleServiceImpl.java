package ru.javamentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.dao.RoleDao;
import ru.javamentor.exception.DBException;

import java.sql.SQLException;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Long getRoleIdByName(String name) throws DBException {
        try {
            return roleDao.getRoleIdByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
