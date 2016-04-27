package com.knook.dao;

import com.knook.model.ConnectionType;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConnectionTypeDao extends AbstractDao<ConnectionType> {

    public ConnectionTypeDao() {
        super(ConnectionType.class);
    }

    public ConnectionType findByName(String name){
        ConnectionType entity = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from " + this.clazz.getSimpleName() + " e where e.name = :NAME");
            query.setParameter("NAME", name);
            entity = (ConnectionType) query.uniqueResult();
            Hibernate.initialize(entity);
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

        return entity;
    }

}
