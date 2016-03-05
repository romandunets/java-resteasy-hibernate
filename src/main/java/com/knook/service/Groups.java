package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.model.Group;
import com.knook.serializer.GroupSerializer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users/{user_id}/groups")
public class Groups {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Group.class, new GroupSerializer());
    private Gson gson = builder.create();

    private GroupDao groupDao = new GroupDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String list(@PathParam("user_id") Long userId) {
        return gson.toJson(groupDao.listForUser(userId));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        return gson.toJson(groupDao.getForUser(userId, id));
    }

}
