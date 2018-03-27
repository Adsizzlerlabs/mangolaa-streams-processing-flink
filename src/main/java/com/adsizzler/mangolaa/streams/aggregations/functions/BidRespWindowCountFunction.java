package com.adsizzler.mangolaa.streams.aggregations.functions;

import com.adsizzler.mangolaa.streams.aggregations.AggregatedBidResp;
import com.adsizzler.mangolaa.streams.domain.BidResp;
import lombok.val;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by ankushsharma on 23/02/18.
 */
public class BidRespWindowCountFunction implements WindowFunction<BidResp, AggregatedBidResp, Tuple6<Integer, Integer, Integer,Integer, Integer, ZonedDateTime>, TimeWindow> {

    @Override
    public void apply(
            final Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime> keys,
            final TimeWindow timeWindow,
            final Iterable<BidResp> bidResponses,
            final Collector<AggregatedBidResp> collector) throws Exception
    {
        val advId = (Integer) keys.getField(0);
        val sourceId = (Integer) keys.getField(1);
        val clientId = (Integer) keys.getField(2);
        val campaignId = (Integer) keys.getField(3);
        val creativeId = (Integer) keys.getField(4);
        val minute = (ZonedDateTime) keys.getField(5);

        val count = Iterables.size(bidResponses);
        val uuid = UUID.randomUUID();

        val aggregation = AggregatedBidResp
                            .builder()
                                .uuid(uuid)
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
