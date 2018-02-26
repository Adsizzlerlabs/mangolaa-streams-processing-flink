package in.ankushs.sample.flink.deserializers;

import in.ankushs.sample.flink.domain.Impression;
import in.ankushs.sample.flink.utils.Gzip;
import in.ankushs.sample.flink.utils.Json;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by ankushsharma on 26/02/18.
 */
@Slf4j
public class ImpressionGzipJsonDeserializer implements DeserializationSchema<Impression> {

    @Override
    public Impression deserialize(final byte[] bytes) throws IOException {
        Impression impression = null;
        if(Objects.nonNull(bytes)){
            try {
                val json = Gzip.decompress(bytes);
                impression = Json.toObject(json, Impression.class);
            }
            catch (final Exception e) {
                log.error("", e.getMessage());
            }

        }
        return impression;
    }

    @Override
    public boolean isEndOfStream(final Impression impression) {
        return false;
    }

    @Override
    public TypeInformation<Impression> getProducedType() {
        return TypeInformation.of(Impression.class);
    }
}
