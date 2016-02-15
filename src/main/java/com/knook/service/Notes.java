package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.NoteDao;
import com.knook.model.Note;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("notes")
public class Notes {

    private GsonBuilder builder = new GsonBuilder();
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
    public String getNotes(@PathParam("id") Long id) {
        return gson.toJson(noteDao.get(id));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(Note note) {
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
    public Response updateNote(Note note, @PathParam("id") Long id) {
        note.setId(id);
        if (noteDao.update(note)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
