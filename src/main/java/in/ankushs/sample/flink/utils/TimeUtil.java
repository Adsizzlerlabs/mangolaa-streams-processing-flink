package in.ankushs.sample.flink.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Utilities to deal with time
 * Created by ankushsharma on 23/02/18.
 */
public final class TimeUtil {

    public static ZonedDateTime roundOffToMinute(ZonedDateTime timestamp){
        Assert.notNull(timestamp, "timestamp cannot be null");
        return ZonedDateTime.of(
                timestamp.toLocalDateTime(),
                ZoneId.of("UTC")
        );
    }
}
