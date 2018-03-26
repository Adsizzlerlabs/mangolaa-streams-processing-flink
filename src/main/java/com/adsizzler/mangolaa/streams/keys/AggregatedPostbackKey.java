package com.adsizzler.mangolaa.streams.keys;

import com.adsizzler.mangolaa.streams.domain.Postback;
import com.adsizzler.mangolaa.streams.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple7;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 26/03/18.
 */
public class AggregatedPostbackKey implements KeySelector<Postback, Tuple7<Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String>> {

    @Override
    public Tuple7<Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String> getKey(final Postback postback) throws Exception {
        val advId = postback.getAdvId();
        val sourceId = postback.getSourceId();
        val clientId = postback.getClientId();
        val campaignId = postback.getCampaignId();
        val creativeId = postback.getCreativeId();
        val event = postback.getEvent();
        val minute  = TimeUtil.roundOffToMinute(postback.getTimestamp());

        //Key = advId, sourceId, clientId, campaignId, creativeId , eventCode, minute
        return new Tuple7<>(
                advId,
                sourceId,
                clientId,
                campaignId,
                creativeId,
                minute,
                event
        );
    }

}
