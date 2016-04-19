package com.knook.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.dao.UserDao;
import com.knook.model.Group;
import com.knook.model.User;
import com.knook.serializer.GroupSerializer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("attachments")
@Produces(MediaType.APPLICATION_JSON)
public class AttachmentsResource {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting();
    private Gson gson = builder.create();

}
