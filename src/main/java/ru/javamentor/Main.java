package ru.javamentor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.javamentor.model.User;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();

        session.save(new User("admin", "1",31, "admin"));
        session.save(new User("nick", "1", 18, "user"));
        session.save(new User("dick", "1", 28, "user"));
        session.save(new User("quick", "1",  68, "user"));
        session.save(new User("vick", "1", 38, "user"));
        session.save(new User("brick", "1", 81, "user"));
        session.save(new User("kick", "1", 9, "user"));
        session.save(new User("pick", "1", 999, "user"));
        session.save(new User("frick", "1", 77, "user"));

        session.close();
    }
}
