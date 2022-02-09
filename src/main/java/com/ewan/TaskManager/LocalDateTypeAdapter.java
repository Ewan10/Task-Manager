package com.ewan.TaskManager;

import java.lang.reflect.Type;
import com.google.gson.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>,
            JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormat.forPattern("dd/MM/yyyy"));
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc,
                JsonSerializationContext context) {
            return new JsonPrimitive(DateTimeFormat.forPattern("dd/MM/yyyy")
                    .print(src));
        }
   
//private Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTime()).create();

 }