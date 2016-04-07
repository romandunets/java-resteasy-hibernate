package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.GroupDao;
import com.knook.dao.NoteDao;
import com.knook.dao.UserDao;
import com.knook.model.Group;
import com.knook.model.Note;
import com.knook.model.User;
import com.knook.serializer.NoteSerializer;
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

@Path("users/{user_id}/groups/{group_id}/notes")
public class Notes {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Note.class, new NoteSerializer());
    private Gson gson = builder.create();

    private UserDao userDao = new UserDao();
    private GroupDao groupDao = new GroupDao();
    private NoteDao noteDao = new NoteDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("user_id") Long userId, @PathParam("group_id") Long groupId) {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);

        if (user != null && group != null) {
            String response = gson.toJson(group.getNotes());
            return Response.ok(response).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("user_id") Long userId, @PathParam("group_id") Long groupId, @PathParam("id") Long id) {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        Note note = noteDao.get(id);

        if (user != null && group != null && note != null && Objects.equals(note.getUser().getId(), user.getId()) && Objects.equals(note.getGroup().getId(), group.getId())) {
            String response = gson.toJson(note);
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
    public Response create(@PathParam("user_id") Long userId, @PathParam("group_id") Long groupId, String json) {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        Note note = gson.fromJson(json, Note.class);
        note.setUser(user);
        note.setGroup(group);

        if (noteDao.create(note)) {
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
    public Response update(@PathParam("user_id") Long userId, @PathParam("group_id") Long groupId, @PathParam("id") Long id, String json) {
        User user = userDao.get(userId);
        Group group = groupDao.get(groupId);
        Note note = gson.fromJson(json, Note.class);
        note.setId(id);
        note.setUser(user);
        note.setGroup(group);

        if (user != null && group != null && noteDao.update(note)) {
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
        Note note = noteDao.get(id);

        if (user != null && note != null && Objects.equals(note.getUser().getId(), user.getId()) && noteDao.delete(note)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
