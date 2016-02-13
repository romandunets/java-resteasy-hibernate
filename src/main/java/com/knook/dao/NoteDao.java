package com.knook.dao;

import com.knook.model.Note;
import com.knook.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NoteDao {
    
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    
    
}
