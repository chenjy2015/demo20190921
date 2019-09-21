package com.example.myapplication;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class GsonIntegerDefaultAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

    @Override
    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }


    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || "null" == json.getAsString()) {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            return json.getAsInt();
        }catch (NumberFormatException e){
            throw new JsonSyntaxException(e);
        }
    }
}
