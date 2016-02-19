package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.NoteDao;
import com.knook.dao.UserDao;
import com.knook.model.Note;
import com.knook.model.User;
import com.knook.serializer.NoteSerializer;
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

    private NoteDao noteDao = new NoteDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String listNotes() {
        return gson.toJson(noteDao.list());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNote(@PathParam("id") Long id) {
        return gson.toJson(noteDao.get(id));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(@PathParam("user_id") Long userId, String json) {
        UserDao userDao = new UserDao();
        User user = userDao.get(userId);
        Note note = gson.fromJson(json, Note.class);
        note.setUser(user);

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
    public Response updateNote(@PathParam("user_id") Long userId, @PathParam("id") Long id, String json) {
        UserDao userDao = new UserDao();
        User user = userDao.get(userId);
        Note note = gson.fromJson(json, Note.class);
        note.setId(id);
        note.setUser(user);

        if (noteDao.update(note)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNote(@PathParam("id") Long id) {
        Note note = noteDao.get(id);
        if (note != null && noteDao.delete(note)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
