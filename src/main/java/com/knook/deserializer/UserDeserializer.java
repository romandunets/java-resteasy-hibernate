package com.knook.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.knook.model.User;
import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        String email = json.getAsJsonObject().get("email").getAsString();
        String password = json.getAsJsonObject().get("password").getAsString();

        User user = new User(email, password);

        return user;
    }

}
