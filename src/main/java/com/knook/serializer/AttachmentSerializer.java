package com.knook.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.knook.model.Attachment;
import java.lang.reflect.Type;

public class AttachmentSerializer implements JsonSerializer<Attachment> {

    @Override
    public JsonElement serialize(Attachment src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("id", src.getId());
        object.addProperty("filename", src.getFilename());
        object.addProperty("note_id", src.getNote().getId());
        object.addProperty("created_at", src.getCreatedAt().toString());
        object.addProperty("updated_at", src.getUpdatedAt().toString());

        return object;
    }

}
