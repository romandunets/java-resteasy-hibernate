package com.knook.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.knook.model.Connection;
import java.lang.reflect.Type;

public class ConnectionDeserializer implements JsonDeserializer<Connection> {

    @Override
    public Connection deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        Connection connection = new Connection();
        return connection;
    }

}