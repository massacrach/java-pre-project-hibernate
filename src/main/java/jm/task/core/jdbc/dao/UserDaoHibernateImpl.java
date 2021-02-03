package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String queryText = "CREATE TABLE users (id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age SMALLINT);";
            Query query = session.createNativeQuery(queryText);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String queryText = "DROP TABLE USERS;";
            Query query = session.createNativeQuery(queryText);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);

            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = new User();
            user.setId(id);

            session.delete(user);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            String hql = "from User";
            Query query = session.createQuery(hql);

            users = (List<User>) query.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "delete from User";
            Query query = session.createQuery(hql);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
