package com.adsizzler.mangolaa.streams.aggregations.functions;

import com.adsizzler.mangolaa.streams.aggregations.AggregatedBidReq;
import com.adsizzler.mangolaa.streams.domain.BidReq;
import lombok.val;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.shaded.guava18.com.google.common.collect.Iterables;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Count bid req in a WindowedStream
 * Created by ankushsharma on 23/02/18.
 */
public class BidReqWindowCountFunction implements WindowFunction<BidReq, AggregatedBidReq, Tuple4<Integer, Integer, Integer, ZonedDateTime>, TimeWindow> {

    @Override
    public void apply(
            final Tuple4<Integer, Integer, Integer, ZonedDateTime> keys,
            final TimeWindow timeWindow,
            final Iterable<BidReq> bidReqs,
            final Collector<AggregatedBidReq> collector
    ) throws Exception
    {
        val advId = (Integer) keys.getField(0);
        val sourceId = (Integer) keys.getField(1);
        val clientId = (Integer) keys.getField(2);
        val minute = (ZonedDateTime) keys.getField(3);

        val count = Iterables.size(bidReqs);
        val uuid = UUID.randomUUID();

        val aggregation = AggregatedBidReq
                                .builder()
                                    .uuid(uuid)
                                    .advId(advId)
                                    .sourceId(sourceId)
                                    .clientId(clientId)
                                    .timestamp(minute)
                                    .count(count)
                                .build();

        collector.collect(aggregation);
    }
}
