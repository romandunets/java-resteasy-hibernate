package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class Users {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    private static Map<Integer, User> users = new HashMap<Integer, User>();
    static {
        users.put(1, new User(1l, "user_1@test.com", "password"));
        users.put(2, new User(2l, "user_2@test.com", "password"));
    }
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String listUsers() {
        return gson.toJson(users);
    }
}
