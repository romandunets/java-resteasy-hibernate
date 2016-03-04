package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.model.Group;
import com.knook.serializer.GroupSerializer;
import javax.ws.rs.Path;

@Path("users/{user_id}/groups")
public class Groups {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Group.class, new GroupSerializer());
    private Gson gson = builder.create();

    private GroupDao groupDao = new GroupDao();

}
