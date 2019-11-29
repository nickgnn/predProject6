package ru.javamentor.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javamentor.config.hibernateConfig.HibernateConfig;

import java.sql.SQLException;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {
    private HibernateConfig hibernateConfig;
    private Session session;

    @Autowired
    public RoleDaoImpl(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    private Session createNewSession() {
        return hibernateConfig.createSessionFactory().openSession();
    }

    @Override
    public Long getRoleIdByName(String name) throws SQLException {
        Long id;
        this.session = createNewSession();

        Transaction transaction =  session.beginTransaction();

        String hql = "SELECT R.id FROM Role R WHERE R.rolename = :roleName";
        Query query = session.createQuery(hql);
        query.setParameter("roleName", name);
        List results = query.list();

        id = (Long) results.get(0);

        transaction.commit();

        return id;
    }

    @Override
    public void addRoles(Long user_id, Long role_id) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("INSERT INTO user_roles VALUES(" + user_id + ", " + role_id + ");").executeUpdate();

        transaction.commit();
        session.close();
    }
}
