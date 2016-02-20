package com.knook.dao;

import com.knook.model.Note;
import com.knook.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NoteDao {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Note> list() {
        List<Note> notes = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Note n");
            notes = query.list();
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

        return notes;
    }

    public Note get(Long id) {
        Note note = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Note n where n.id = :ID");
            query.setParameter("ID", id);
            note = (Note) query.uniqueResult();
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

        return note;
    }

    public boolean create(Note note) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(note);
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

    public boolean update(Note note) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(note);
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

    public boolean delete(Note note) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(note);
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

    public List<Note> listForUser(Long userId) {
        List<Note> notes = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Note n where n.user.id = :USER_ID");
            query.setParameter("USER_ID", userId);
            notes = query.list();
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

        return notes;
    }

    public Note getForUser(Long userId, Long id) {
        Note note = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Note n where n.user.id = :USER_ID and n.id = :ID");
            query.setParameter("USER_ID", userId);
            query.setParameter("ID", id);
            note = (Note) query.uniqueResult();
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

        return note;
    }

}
