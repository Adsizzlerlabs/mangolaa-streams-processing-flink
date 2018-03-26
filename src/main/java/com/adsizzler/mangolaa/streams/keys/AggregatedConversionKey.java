package com.adsizzler.mangolaa.streams.keys;

import com.adsizzler.mangolaa.streams.domain.Conversion;
import com.adsizzler.mangolaa.streams.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple7;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 26/03/18.
 */
public class AggregatedConversionKey implements KeySelector<Conversion, Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime>> {

    @Override
    public Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime> getKey(final Conversion conversion) throws Exception {
        val advId = conversion.getAdvId();
        val sourceId = conversion.getSourceId();
        val clientId = conversion.getClientId();
        val campaignId = conversion.getCampaignId();
        val creativeId = conversion.getCreativeId();
        val eventCode = conversion.getEventCode();
        val minute  = TimeUtil.roundOffToMinute(conversion.getTimestamp());

        //Key = advId, sourceId, clientId, campaignId, creativeId , eventCode, minute
        return new Tuple7<>(
                advId,
                sourceId,
                clientId,
                campaignId,
                creativeId,
                eventCode,
                minute
        );

    }
}
