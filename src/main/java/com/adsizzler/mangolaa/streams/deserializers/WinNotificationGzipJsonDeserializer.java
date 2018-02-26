package com.adsizzler.mangolaa.streams.deserializers;

import com.adsizzler.mangolaa.streams.domain.WinNotification;
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
public class WinNotificationGzipJsonDeserializer implements DeserializationSchema<WinNotification> {

    @Override
    public WinNotification deserialize(final byte[] bytes) throws IOException {
        WinNotification winNotification = null;
        if(Objects.nonNull(bytes)){
            try {
                val json = Gzip.decompress(bytes);
                winNotification = Json.toObject(json, WinNotification.class);
            }
            catch (final Exception e) {
                log.error("", e.getMessage());
            }

        }
        return winNotification;

    }

    @Override
    public boolean isEndOfStream(final WinNotification winNotification) {
        return false;
    }

    @Override
    public TypeInformation<WinNotification> getProducedType() {
        return TypeInformation.of(WinNotification.class);
    }
}
