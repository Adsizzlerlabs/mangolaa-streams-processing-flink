package com.adsizzler.mangolaa.streams.deserializers;

import com.adsizzler.mangolaa.streams.domain.BidReq;
import com.adsizzler.mangolaa.streams.utils.Gzip;
import com.adsizzler.mangolaa.streams.utils.Json;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by ankushsharma on 23/02/18.
 */
@Slf4j
public class BidReqGzipJsonDeserializer implements DeserializationSchema<BidReq> {

    @Override
    public BidReq deserialize(final byte[] bytes) throws IOException {
        BidReq bidReq = null;
        if(Objects.nonNull(bytes)){
            try {
                val json = Gzip.decompress(bytes);
                bidReq = Json.toObject(json, BidReq.class);
            }
            catch (final Exception e) {
                log.error("", e.getMessage());
            }

        }
        return bidReq;
    }

    @Override
    public boolean isEndOfStream(final BidReq bidReq) {
        return false;
    }

    @Override
    public TypeInformation<BidReq> getProducedType() {
        return TypeInformation.of(BidReq.class);
    }
}
