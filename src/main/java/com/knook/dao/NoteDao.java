package com.knook.dao;

import com.knook.model.Note;
import com.knook.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class NoteDao extends AbstractDao<Note> {

    public NoteDao() {
        super(Note.class);
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

    @Override
    protected void initializeEntity(Note note) {}

}
