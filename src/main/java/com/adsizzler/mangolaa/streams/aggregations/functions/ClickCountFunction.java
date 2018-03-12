package com.adsizzler.mangolaa.streams.aggregations.functions;

import com.adsizzler.mangolaa.streams.aggregations.AggregatedBidReq;
import com.adsizzler.mangolaa.streams.aggregations.AggregatedBidResp;
import com.adsizzler.mangolaa.streams.aggregations.AggregatedClick;
import com.adsizzler.mangolaa.streams.aggregations.AggregatedImpression;
import com.adsizzler.mangolaa.streams.domain.BidResp;
import com.adsizzler.mangolaa.streams.domain.Click;
import lombok.val;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 12/03/18.
 */
public class ClickCountFunction implements WindowFunction<Click, AggregatedClick, Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime>, TimeWindow> {

    @Override
    public void apply(
            final Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime> keys,
            final TimeWindow timeWindow,
            final Iterable<Click> clicks,
            final Collector<AggregatedClick> collector
    )
    throws Exception
    {
        val advId = (Integer) keys.getField(0);
        val sourceId = (Integer) keys.getField(1);
        val clientId = (Integer) keys.getField(2);
        val campaignId = (Integer) keys.getField(3);
        val creativeId = (Integer) keys.getField(4);
        val minute = (ZonedDateTime) keys.getField(5);

        val count = Iterables.size(clicks);

        val aggregation = AggregatedClick
                            .builder()
                                .advId(advId)
                                .sourceId(sourceId)
                                .clientId(clientId)
                                .campaignId(campaignId)
                                .creativeId(creativeId)
                                .timestamp(minute)
                                .count(count)
                            .build();
        collector.collect(aggregation);

    }

}
