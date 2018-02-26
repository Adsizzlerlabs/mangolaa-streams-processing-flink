package com.adsizzler.mangolaa.streams.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.adsizzler.mangolaa.streams.utils.Strings;
import lombok.val;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ankushsharma on 23/02/18.
 */
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        val text = jsonParser.getText();
        ZonedDateTime result = null;
        if(Strings.hasText(text)){
            result = ZonedDateTime.parse(
                    text,
                    DateTimeFormatter.ISO_ZONED_DATE_TIME
            );
        }
        return result;
    }

}
