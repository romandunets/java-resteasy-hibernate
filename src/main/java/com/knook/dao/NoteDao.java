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
    
}
