package com.knook.dao;

import com.knook.model.User;
import com.knook.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {
    
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public List<User> list() {
        List<User> users = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from User u");
            users = query.list();
            session.getTransaction().commit();
        }
        catch (Exception exception) {
           if (session != null) {
               session.getTransaction().rollback();
           }
        }
        finally {
            if (session != null) {
               session.close();
            }
        }

        return users;
    }

    public User get(Long id) {
        User user = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from User u where u.id = :ID");
            query.setParameter("ID", id);
            user = (User) query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (Exception exception) {
           if (session != null) {
               session.getTransaction().rollback();
           }
        }
        finally {
            if (session != null) {
               session.close();
            }
        }

        return user;
    }
    
    public boolean create(User user) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            success = true;
        }
        catch (Exception exception) {
           if (session != null) {
               session.getTransaction().rollback();
           }
        }
        finally {
            if (session != null) {
               session.close();
            }
        }

        return success;
    }

}
