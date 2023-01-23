package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class Util {
    public static final SessionFactory factory;
    static {
        try {
            Properties prop= new Properties();
            //provide the required properties
            prop.setProperty("hibernate.connection.url",  "jdbc:mysql://localhost:3306/users");
            prop.setProperty("hibernate.connection.username", "roo");
            prop.setProperty("hibernate.connection.password", "root");
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            prop.setProperty(Environment.HBM2DDL_AUTO, "update");
            //create a configuration
            Configuration config = new Configuration();
            //provide all properties to this configuration
            config.setProperties(prop);
            //add classes which are mapped to database tables.
            config.addAnnotatedClass(User.class);
            //initialize session factory
            factory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Ошибка при подключении.");
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return factory.getCurrentSession();
    }


    // Подключение JDBC
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String USERNAME = "roo";
    private final static String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установено.");
        } catch (SQLException e) {
            System.out.println("Ошибка при установлении соединения.");
        }
        return connection;
    }

}
