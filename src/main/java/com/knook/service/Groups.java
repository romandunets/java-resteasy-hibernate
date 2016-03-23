package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.dao.UserDao;
import com.knook.model.Group;
import com.knook.model.User;
import com.knook.serializer.GroupSerializer;
import java.util.Objects;
import java.util.Set;
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

    private UserDao userDao = new UserDao();
    private GroupDao groupDao = new GroupDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("user_id") Long userId) {
        User user = userDao.get(userId);

        if (user != null) {
            String response = gson.toJson(user.getGroups());
            return Response.ok(response).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        User user = userDao.get(userId);
        Group group = groupDao.get(id);

        if (user != null && group != null && Objects.equals(group.getUser().getId(), user.getId())) {
            String response = gson.toJson(group);
            return Response.ok(response).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("user_id") Long userId, String json) {
        User user = userDao.get(userId);
        Group group = gson.fromJson(json, Group.class);
        group.setUser(user);

        if (groupDao.create(group)) {
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
        User user = userDao.get(userId);
        Group group = gson.fromJson(json, Group.class);
        group.setId(id);

        if (user != null && Objects.equals(group.getUser().getId(), user.getId()) && groupDao.update(group)) {
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
        User user = userDao.get(userId);
        Group group = groupDao.get(id);

        if (group != null && Objects.equals(group.getUser().getId(), user.getId()) && groupDao.delete(group)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}/children")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listChildren(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        User user = userDao.get(userId);
        Group group = groupDao.get(id);
        
        if (user != null && Objects.equals(group.getUser().getId(), user.getId())) {
            Set<Group> children = group.getChildren();
            return Response.ok(gson.toJson(children)).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{id}/children")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChild(@PathParam("user_id") Long userId, @PathParam("id") Long id, String json) {
        User user = userDao.get(userId);
        Group parent = groupDao.get(id);
        Group group = gson.fromJson(json, Group.class);

        group.setUser(user);
        group.setParent(parent);

        if (user != null && parent != null && Objects.equals(parent.getUser().getId(), user.getId()) && groupDao.create(group)) {
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
        Group group = groupDao.get(childId);

        if (group != null && Objects.equals(group.getUser().getId(), userId) && groupDao.delete(group)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
