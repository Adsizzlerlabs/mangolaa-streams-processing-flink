package com.adsizzler.mangolaa.streams.deserializers;

import com.adsizzler.mangolaa.streams.domain.BidReq;
import com.adsizzler.mangolaa.streams.domain.BidResp;
import com.adsizzler.mangolaa.streams.domain.Click;
import com.adsizzler.mangolaa.streams.utils.Gzip;
import com.adsizzler.mangolaa.streams.utils.Json;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by ankushsharma on 12/03/18.
 */
@Slf4j
public class ClickGzipJsonDeserializer implements DeserializationSchema<Click> {

    @Override
    public Click deserialize(byte[] bytes) throws IOException {
        Click click = null;
        if(Objects.nonNull(bytes)){
            try {
                val json = Gzip.decompress(bytes);
                click = Json.toObject(json, Click.class);
            }
            catch (final Exception e) {
                log.error("", e.getMessage());
            }

        }
        return click;
    }

    @Override
    public boolean isEndOfStream(Click click) {
        return false;
    }

    @Override
    public TypeInformation<Click> getProducedType() {
        return TypeInformation.of(Click.class);
    }
}
