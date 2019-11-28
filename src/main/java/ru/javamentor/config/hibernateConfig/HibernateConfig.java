package ru.javamentor.config.hibernateConfig;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;
import ru.javamentor.model.Role;
import ru.javamentor.model.User;
import ru.javamentor.model.UserRoles;

@Component
public class HibernateConfig {
    public SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(UserRoles.class);

        return configuration;
    }
}
