package ru.javamentor.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javamentor.config.HibernateConfig;
import ru.javamentor.model.User;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoByHibernate implements UserDao {
    @Autowired
    private HibernateConfig hibernateConfig;
    private Session session;

    private Session createNewSession() {
        return hibernateConfig.createSessionFactory().openSession();
    }

    @Override
    public void addUser(String name, int age) throws SQLException {
        this.session = createNewSession();

        User user = getUserByName(name);

        if (user == null) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, age));
            transaction.commit();
            session.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    public void addUser(String name, int age, String password, String role) throws SQLException {
        this.session = createNewSession();

        User user = getUserByName(name);

        if (user == null) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, age, password, role));
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

        Transaction transaction =  session.beginTransaction();

        String hql = "FROM User WHERE name = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", name);
        List<User> users = query.list();

        transaction.commit();

        if (users.size() == 0) {
            return null;
        }

        session.close();

        return users.get(0);
    }

    @Override
    public long getUserIdByName(String name) throws SQLException {
        long id;
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "SELECT U.id FROM User U WHERE U.name = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", name);
        List results = query.list();

        id = (long) results.get(0);

        transaction.commit();
        session.close();

        return id;
    }

    @Override
    public boolean isExistsUser(String name) throws SQLException {
        if (getUserByName(name) == null) {
            return false;
        }

        return true;
    }

    @Override
    public void updateUser(User user, String name, int age, String password, String role) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        user.setName(name);
        user.setAge(age);
        user.setPassword(password);
        user.setRole(role);

        session.update(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserByName(String name) throws SQLException {
        this.session = createNewSession();

        Transaction transaction = session.beginTransaction();

        String hql = "DELETE User WHERE name = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", name);

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
