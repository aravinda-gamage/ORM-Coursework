package lk.ijse.ormCoursework.util;

import lk.ijse.ormCoursework.entity.Book;
import lk.ijse.ormCoursework.entity.Branches;
import lk.ijse.ormCoursework.entity.Detail;
import lk.ijse.ormCoursework.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configuration
                .addAnnotatedClass(User.class).addAnnotatedClass(Book.class).addAnnotatedClass(Detail.class).addAnnotatedClass(Branches.class);
        sessionFactory=configuration.setProperties(properties).buildSessionFactory();
    }
    public static SessionFactoryConfig getInstance() {
        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
