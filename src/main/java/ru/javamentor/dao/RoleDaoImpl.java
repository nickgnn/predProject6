package ru.javamentor.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getRoleIdByName(String name) throws SQLException {
        Long id;

        String hql = "SELECT R.id FROM Role R WHERE R.rolename = :roleName";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("roleName", name);
        List results = query.list();

        id = (Long) results.get(0);

        return id;
    }

    @Override
    public void addRoles(Long user_id, Long role_id) throws SQLException {
        entityManager.createNativeQuery("INSERT INTO user_roles VALUES(" + user_id + ", " + role_id + ");").executeUpdate();
    }
}
