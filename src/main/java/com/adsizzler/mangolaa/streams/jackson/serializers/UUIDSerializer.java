package com.adsizzler.mangolaa.streams.jackson.serializers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by ankushsharma on 27/03/18.
 */
public class UUIDSerializer extends JsonSerializer<UUID> {

    @Override
    public void serialize(final UUID uuid,final JsonGenerator jsonGenerator,final SerializerProvider serializerProvider) throws IOException {
        if(Objects.nonNull(uuid)){
            jsonGenerator.writeString(uuid.toString());
        }
    }
}
