package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import org.hibernate.SessionFactory;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {


    private Connection connection;

    private SessionFactory factory;

    private final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private final String USERNAME = "root";
    private final String PASSWORD = "Terminatoratm123.";

    /**
     * JDBC config
     * @return Connection
     */
    public Connection getConnection () {
        try {
            if (!connection.isClosed() && connection != null) {
                System.out.println("Connection has been made");
                return connection;
            } else {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connection has been made");
                return connection;
            }
        } catch (SQLException e) {
            System.out.println("Failed: Сonnection has NOT been established");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Hibernate config
     * @return SessionFactory
     */
    public SessionFactory getFactory () {
        if (factory == null) {
            try {
                Configuration config = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                config.setProperties(settings);
                config.addAnnotatedClass(User.class);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

                factory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception exception) {
                System.out.println("Failed: GETFACTORY method has NOT been established");
                exception.printStackTrace();
            }
        }
        System.out.println("GETFACTORY method has been established");
        return factory;
    }
}
