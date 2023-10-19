package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory factory;

    Util util;
    Transaction transaction;

    public UserDaoHibernateImpl() {
        util = new Util();
        factory = util.getFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session s = factory.openSession()) {
            transaction = s.beginTransaction();
            s.createSQLQuery("CREATE TABLE IF NOT EXISTS User (\n" +
                            "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(45) ,\n" +
                            "  `lastName` VARCHAR(45) ,\n" +
                            "  `age` TINYINT ,\n" +
                            "  PRIMARY KEY (`id`));\n")
                    .executeUpdate();
            transaction.commit();
            System.out.println("DBTable users has been created");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session s = factory.openSession()) {
            transaction = s.beginTransaction();
            s.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
            System.out.println("DBTable \"users\" has been dropped");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session s = factory.openSession()) {
            transaction = s.beginTransaction();
            s.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User name:" + name + "; lastName:" + lastName + "; age:" + age + " has been saved");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.delete(session.get(User.class, id));
                session.getTransaction().commit();
                System.out.println("User id:" + id + " has been removed");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session s = factory.openSession()) {
            transaction = s.beginTransaction();
            list = s.createQuery("from User").getResultList();
            transaction.commit();
            System.out.println("All users of the table \"users\" have been sent");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session s = factory.openSession()) {
            transaction = s.beginTransaction();
            s.createQuery("delete User").executeUpdate();
            transaction.commit();
            System.out.println("Table \"users\" has been cleared");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
