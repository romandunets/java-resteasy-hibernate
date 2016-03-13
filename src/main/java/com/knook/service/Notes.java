package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.NoteDao;
import com.knook.dao.UserDao;
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

@Path("users/{user_id}/notes")
public class Notes {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Note.class, new NoteSerializer());
    private Gson gson = builder.create();

    private UserDao userDao = new UserDao();
    private NoteDao noteDao = new NoteDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String list(@PathParam("user_id") Long userId) {
        User user = userDao.get(userId);
        return gson.toJson(user.getNotes());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("user_id") Long userId, @PathParam("id") Long id) {
        return gson.toJson(noteDao.getForUser(userId, id));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("user_id") Long userId, String json) {
        Note note = gson.fromJson(json, Note.class);
        if (noteDao.createForUser(note, userId)) {
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
        Note note = gson.fromJson(json, Note.class);
        note.setId(id);
        if (noteDao.updateForUser(note, userId)) {
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
        Note note = noteDao.getForUser(userId, id);
        if (note != null && Objects.equals(note.getUser().getId(), userId) && noteDao.delete(note)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
