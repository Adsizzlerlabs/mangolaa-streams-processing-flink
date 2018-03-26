package com.adsizzler.mangolaa.streams.aggregations.functions;

import com.adsizzler.mangolaa.streams.aggregations.AggregatedPostback;
import com.adsizzler.mangolaa.streams.domain.Postback;
import lombok.val;
import org.apache.flink.api.java.tuple.Tuple7;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 26/03/18.
 */
public class PostbackWindowCountFunction implements WindowFunction<Postback, AggregatedPostback, Tuple7<Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String>, TimeWindow> {

    @Override
    public void apply(
            final Tuple7<Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String> keys,
            final TimeWindow timeWindow,
            final Iterable<Postback> postbacks,
            final Collector<AggregatedPostback> collector
    )
    throws Exception
    {
        val advId = (Integer) keys.getField(0);
        val sourceId = (Integer) keys.getField(1);
        val clientId = (Integer) keys.getField(2);
        val campaignId = (Integer) keys.getField(3);
        val creativeId = (Integer) keys.getField(4);
        val minute = (ZonedDateTime) keys.getField(5);
        val event = (String) keys.getField(6);

        val count = Iterables.size(postbacks);

        val aggregation = AggregatedPostback
                .builder()
                    .advId(advId)
                    .sourceId(sourceId)
                    .clientId(clientId)
                    .campaignId(campaignId)
                    .creativeId(creativeId)
                    .timestamp(minute)
                    .event(event)
                    .count(count)
                .build();

        collector.collect(aggregation);
    }
}
