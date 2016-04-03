package com.knook.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.knook.model.Group;
import java.lang.reflect.Type;

public class GroupSerializer implements JsonSerializer<Group> {

    @Override
    public JsonElement serialize(Group src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("id", src.getId());
        object.addProperty("name", src.getName());
        object.addProperty("description", src.getDescription());

        if (src.getParent() != null) {
          object.addProperty("parent_group_id", src.getParent().getId());
        }

        object.addProperty("created_at", src.getCreatedAt().toString());
        object.addProperty("updated_at", src.getUpdatedAt().toString());

        return object;
    }

}
