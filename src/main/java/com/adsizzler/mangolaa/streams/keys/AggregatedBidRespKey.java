package com.adsizzler.mangolaa.streams.keys;

import com.adsizzler.mangolaa.streams.domain.BidResp;
import com.adsizzler.mangolaa.streams.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple6;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 23/02/18.
 */
public class AggregatedBidRespKey implements KeySelector<BidResp, Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime>> {

    @Override
    public Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime> getKey(final BidResp bidResp) throws Exception {
        val advId = bidResp.getAdvId();
        val sourceId = bidResp.getSourceId();
        val clientId = bidResp.getClientId();
        val campaignId = bidResp.getCampaignId();
        val creativeId = bidResp.getCreativeId();
        val minute  = TimeUtil.roundOffToMinute(bidResp.getTimestamp());

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
