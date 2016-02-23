package com.knook.dao;

import com.knook.model.User;
import org.hibernate.Hibernate;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    @Override
    protected void initializeEntity(User user) {
        Hibernate.initialize(user.getNotes());
    }

}
