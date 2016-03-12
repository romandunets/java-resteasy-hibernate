package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.model.Group;
import com.knook.serializer.GroupSerializer;
import java.util.Objects;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("user_id") Long userId, String json) {
        Group group = gson.fromJson(json, Group.class);
        if (groupDao.createForUser(group, userId)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("user_id") Long userId, @PathParam("id") Long id, String json) {
        Group group = gson.fromJson(json, Group.class);
        group.setId(id);
        if (groupDao.updateForUser(group, userId)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        Group group = groupDao.getForUser(userId, id);
        if (group != null && Objects.equals(group.getUser().getId(), userId) && groupDao.delete(group)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}/children")
    @Produces(MediaType.APPLICATION_JSON)
    public String listChildren(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        Group group = groupDao.getForUser(userId, id);
        return gson.toJson(group.getChildren());
    }

    @POST
    @Path("/{id}/children")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChild(@PathParam("user_id") Long userId, @PathParam("id") Long id, String json) {
        Group parent = groupDao.getForUser(userId, id);
        Group group = gson.fromJson(json, Group.class);
        group.setParent(parent);
        if (groupDao.createForUser(group, userId)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{id}/children/{child_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeChild(@PathParam("user_id") Long userId, @PathParam("id") Long id, @PathParam("child_id") Long childId, String json) {
        Group group = groupDao.getForUser(userId, childId);
        if (group != null && Objects.equals(group.getUser().getId(), userId) && groupDao.delete(group)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
