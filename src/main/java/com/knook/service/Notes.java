package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.NoteDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

}
