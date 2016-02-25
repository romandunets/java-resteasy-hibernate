package com.knook.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.knook.model.User;
import java.lang.reflect.Type;

public class UserSerializer implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("id", src.getId());
        object.addProperty("email", src.getEmail());
        object.addProperty("created_at", src.getCreatedAt().toString());

        return object;
    }

}
