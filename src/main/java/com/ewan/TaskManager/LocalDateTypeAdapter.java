package com.ewan.TaskManager;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>,
            JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString());
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc,
                JsonSerializationContext context) {
            return new JsonPrimitive(ISODateTimeFormat
                    .dateTimeNoMillis()
                    .print(src));
        }
   
private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDate()).create();

 Gson getGson() {
    return gson;
}
 }