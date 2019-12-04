package ru.javamentor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.exception.DBException;
import ru.javamentor.repository.RoleRepository;
import ru.javamentor.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Long getRoleIdByName(String name) throws DBException {
        return roleRepository.findRoleByRolename(name).get().getId();
    }

    @Override
    public void addRoles(Long user_id, Long role_id) throws DBException {
        roleRepository.insertRoles(user_id, role_id);
    }
}
