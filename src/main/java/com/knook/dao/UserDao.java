package com.knook.dao;

import com.knook.model.User;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

}
