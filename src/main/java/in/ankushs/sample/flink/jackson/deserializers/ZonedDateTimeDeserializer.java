package in.ankushs.sample.flink.jackson.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import in.ankushs.sample.flink.utils.Strings;
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
