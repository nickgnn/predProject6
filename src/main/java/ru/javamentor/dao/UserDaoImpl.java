package ru.javamentor.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository("userDaoByHibernate")
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(String name, String password, Integer age, String role, Long role_ID) throws SQLException {
        User user = getUserByName(name);

        if (user == null) {
            entityManager.persist(new User(name, password, age, role, role_ID));
        } else {
            System.out.println("This name already exists, choose another name:)");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return (List<User>) entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

//    @Override
//    public List<User> getAllUsers() throws SQLException {
//        EntityManager entityManager = factory.createEntityManager();
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//
//        Query query = (Query) entityManager.createQuery("FROM User");
//
//        transaction.commit();
//        entityManager.close();
//
//        return query.getResultList();
//    }

    @Override
    public User getUserByName(String name) throws SQLException {
        return entityManager.find(User.class, name);
    }

    @Override
    public User getUserById(Long ID) throws SQLException {
        return entityManager.find(User.class, ID);
    }

    @Override
    public Long getUserIdByName(String username) throws SQLException {
        Long id;

        String hql = "SELECT U.id FROM User U WHERE U.username = :nameOfUser";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("nameOfUser", username);
        List results = query.list();

        id = (Long) results.get(0);

        entityManager.close();

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
        entityManager.merge(user);
    }

    @Override
    public void deleteUserByName(String name) throws SQLException {
        String hql = "DELETE User WHERE username = :nameOfUser";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("nameOfUser", name);

        query.executeUpdate();

        entityManager.close();
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        String hql = "DELETE User WHERE id = :userID";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("userID", id);

        query.executeUpdate();

        entityManager.close();
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
