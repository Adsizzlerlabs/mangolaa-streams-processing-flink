package com.adsizzler.mangolaa.streams.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Ankush on 17/07/17.
 */
public class Json {

    private Json(){}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toObject(final String json,  final Class<T> clazz) throws Exception {
        Assert.notNull(clazz, "clazz cannot be null");
        return objectMapper
                   .readValue(json,clazz);
    }

    public static String toJson(final Object object) throws Exception {
        Assert.notNull(object, "object cannot be null");
        return objectMapper
                .writer()
                .writeValueAsString(object);
    }
}
