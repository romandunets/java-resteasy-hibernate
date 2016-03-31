package com.knook.dao;

import com.knook.model.Note;

public class NoteDao extends AbstractDao<Note> {

    public NoteDao() {
        super(Note.class);
    }

    @Override
    protected void initializeEntity(Note note) {}

}
