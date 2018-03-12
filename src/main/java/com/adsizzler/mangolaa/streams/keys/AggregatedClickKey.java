package com.adsizzler.mangolaa.streams.keys;

import com.adsizzler.mangolaa.streams.domain.BidResp;
import com.adsizzler.mangolaa.streams.domain.Click;
import com.adsizzler.mangolaa.streams.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple6;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 12/03/18.
 */
public class AggregatedClickKey implements KeySelector<Click, Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime>> {

    @Override
    public Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime> getKey(final Click click) throws Exception {
        val advId = click.getAdvId();
        val sourceId = click.getSourceId();
        val clientId = click.getClientId();
        val campaignId = click.getCampaignId();
        val creativeId = click.getCreativeId();
        val minute  = TimeUtil.roundOffToMinute(click.getTimestamp());

        //Key = advId, sourceId, clientId, campaignId, creativeId , minute
        return new Tuple6<>(
                advId,
                sourceId,
                clientId,
                campaignId,
                creativeId,
                minute
        );

    }
}
