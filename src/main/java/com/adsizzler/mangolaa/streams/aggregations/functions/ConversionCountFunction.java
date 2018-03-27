package com.adsizzler.mangolaa.streams.aggregations.functions;

import com.adsizzler.mangolaa.streams.aggregations.AggregatedConversion;
import com.adsizzler.mangolaa.streams.domain.Conversion;
import lombok.val;
import org.apache.flink.api.java.tuple.Tuple8;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by ankushsharma on 26/03/18.
 */
public class ConversionCountFunction implements WindowFunction<Conversion, AggregatedConversion, Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String>, TimeWindow> {

    @Override
    public void apply(
            final Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String> keys,
            final TimeWindow timeWindow,
            final Iterable<Conversion> conversions,
            final Collector<AggregatedConversion> collector
    )
     throws Exception
    {
        val advId = (Integer) keys.getField(0);
        val sourceId = (Integer) keys.getField(1);
        val clientId = (Integer) keys.getField(2);
        val campaignId = (Integer) keys.getField(3);
        val creativeId = (Integer) keys.getField(4);
        val eventCode = (Integer) keys.getField(5);
        val minute = (ZonedDateTime) keys.getField(6);
        val event = (String) keys.getField(7);

        val count = Iterables.size(conversions);
        val uuid = UUID.randomUUID();

        val aggregation = AggregatedConversion
                            .builder()
                                .uuid(uuid)
                                .advId(advId)
                                .sourceId(sourceId)
                                .clientId(clientId)
                                .campaignId(campaignId)
                                .creativeId(creativeId)
                                .timestamp(minute)
                                .eventCode(eventCode)
                                .event(event)
                                .count(count)
                            .build();

        collector.collect(aggregation);
    }
}
