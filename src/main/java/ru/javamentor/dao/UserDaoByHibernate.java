package ru.javamentor.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javamentor.config.hibernateConfig.HibernateConfig;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoByHibernate implements UserDao {
    private HibernateConfig hibernateConfig;
    private Session session;

    @Autowired
    public UserDaoByHibernate(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    private Session createNewSession() {
        return hibernateConfig.createSessionFactory().openSession();
    }

    @Override
    public void addUser(String name, String password, Integer age, String role, Long role_ID) throws SQLException {
        this.session = createNewSession();

        User user = getUserByName(name);

        if (user == null) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, password, age, role, role_ID));
            transaction.commit();
            session.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        List<User> users = session.createQuery("FROM User").list();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "FROM User WHERE username = :nameOfUser";
        Query query = session.createQuery(hql);
        query.setParameter("nameOfUser", name);
        List<User> users = query.list();

        transaction.commit();

        if (users.size() == 0) {
            return null;
        }

        session.close();

        return users.get(0);
    }

    @Override
    public User getUserById(Long ID) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "FROM User WHERE id = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("ID", ID);
        List<User> users = query.list();

        transaction.commit();

        if (users.size() == 0) {
            return null;
        }

        session.close();

        return users.get(0);
    }

    @Override
    public Long getUserIdByName(String username) throws SQLException {
        Long id;
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "SELECT U.id FROM User U WHERE U.username = :nameOfUser";
        Query query = session.createQuery(hql);
        query.setParameter("nameOfUser", username);
        List results = query.list();

        id = (Long) results.get(0);

        transaction.commit();
        session.close();

        return id;
    }

    @Override
    public Boolean isExistsUser(String name) throws SQLException {
        if (getUserByName(name) == null) {
            return false;
        }

        return true;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserByName(String name) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "DELETE User WHERE username = :nameOfUser";
        Query query = session.createQuery(hql);
        query.setParameter("nameOfUser", name);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "DELETE User WHERE id = :userID";
        Query query = session.createQuery(hql);
        query.setParameter("userID", id);

        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void createTable() throws SQLException {
//        String sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
//                     " `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
//                     " `name` VARCHAR(45) NOT NULL,\n" +
//                     " `age` INT NOT NULL,\n" +
//                     "PRIMARY KEY (`id`))";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.execute();
//        preparedStatement.close();
    }

    @Override
    public void dropTable() throws SQLException {
//        String sql = "DROP TABLE IF EXISTS `users`";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.execute();
//        preparedStatement.close();
    }
}
