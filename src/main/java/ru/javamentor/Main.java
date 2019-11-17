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

        session.save(new User("admin", 31, "1", "admin"));
        session.save(new User("nick", 18, "1", "user"));
        session.save(new User("dick", 28, "1", "user"));
        session.save(new User("quick", 68, "1", "user"));
        session.save(new User("vick", 38, "1", "user"));
        session.save(new User("brick", 81, "1", "user"));
        session.save(new User("kick", 9, "1", "user"));
        session.save(new User("pick", 999, "1", "user"));
        session.save(new User("frick", 77, "1", "user"));

        session.close();
    }
}
