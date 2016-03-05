package com.knook.dao;

import com.knook.model.Group;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDao extends AbstractDao<Group> {

    public GroupDao() {
        super(Group.class);
    }

    public List<Group> listForUser(Long userId) {
        List<Group> groupes = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Group g where g.user.id = :USER_ID");
            query.setParameter("USER_ID", userId);
            groupes = query.list();
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

        return groupes;
    }

    public Group getForUser(Long userId, Long id) {
        Group group = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Group g where g.user.id = :USER_ID and g.id = :ID");
            query.setParameter("USER_ID", userId);
            query.setParameter("ID", id);
            group = (Group) query.uniqueResult();
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

        return group;
    }

    @Override
    protected void initializeEntity(Group group) {}

}
