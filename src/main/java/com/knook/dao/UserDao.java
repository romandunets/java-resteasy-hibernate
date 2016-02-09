package com.knook.dao;

import com.knook.model.User;
import com.knook.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {
    
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    
    
}
