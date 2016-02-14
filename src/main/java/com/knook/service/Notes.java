package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.NoteDao;
import javax.ws.rs.Path;

@Path("notes")
public class Notes {
    
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    
    private NoteDao noteDao = new NoteDao();
    
}
