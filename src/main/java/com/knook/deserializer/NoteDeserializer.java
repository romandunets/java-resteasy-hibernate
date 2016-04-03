package com.knook.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.knook.model.Note;
import java.lang.reflect.Type;

public class NoteDeserializer implements JsonDeserializer<Note> {

    @Override
    public Note deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        String title = json.getAsJsonObject().get("title").getAsString();
        String content = json.getAsJsonObject().get("content").getAsString();
        
        Note note = new Note(title, content);
        return note;
    }

}
