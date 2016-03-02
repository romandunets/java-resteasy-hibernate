package com.knook.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.knook.model.Note;
import java.lang.reflect.Type;

public class NoteSerializer implements JsonSerializer<Note> {

    @Override
    public JsonElement serialize(Note src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("id", src.getId());
        object.addProperty("title", src.getTitle());
        object.addProperty("content", src.getContent());
        object.addProperty("user_id", src.getUser().getId());
        object.addProperty("group_id", src.getGroup().getId());
        object.addProperty("created_at", src.getCreatedAt().toString());
        object.addProperty("updated_at", src.getUpdatedAt().toString());

        return object;
    }

}
