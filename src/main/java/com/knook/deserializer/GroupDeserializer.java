package com.knook.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.knook.model.Group;
import java.lang.reflect.Type;

public class GroupDeserializer implements JsonDeserializer<Group> {

    @Override
    public Group deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        String name = json.getAsJsonObject().get("title").getAsString();
        String description = json.getAsJsonObject().get("content").getAsString();
        
        Group group = new Group(name, description);

        return group;
    }

}