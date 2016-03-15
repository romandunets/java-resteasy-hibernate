package com.knook.dao;

import com.knook.model.Group;
import com.knook.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDao extends AbstractDao<Group> {

    public GroupDao() {
        super(Group.class);
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

    public boolean updateForUser(Group group, Long userId) {
        Boolean success = false;
        Session session = null;
        User user = new User(userId);
        group.setUser(user);

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(group);
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

    @Override
    protected void initializeEntity(Group group) {
    }

}
