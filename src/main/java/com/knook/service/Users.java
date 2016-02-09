package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.UserDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class Users {
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    
    private UserDao userDao = new UserDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String listUsers() {
        return gson.toJson(userDao.list());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") Long id) {
        return gson.toJson(userDao.get(id));
    }
}
