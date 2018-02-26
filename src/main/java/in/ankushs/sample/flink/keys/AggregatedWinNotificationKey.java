package in.ankushs.sample.flink.keys;

import in.ankushs.sample.flink.domain.WinNotification;
import in.ankushs.sample.flink.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple6;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 23/02/18.
 */
public class AggregatedWinNotificationKey implements KeySelector<WinNotification, Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime>> {

    @Override
    public Tuple6<Integer, Integer, Integer, Integer, Integer, ZonedDateTime> getKey(final WinNotification winNotification) throws Exception {
        val advId = winNotification.getAdvId();
        val sourceId = winNotification.getSourceId();
        val clientId = winNotification.getClientId();
        val campaignId = winNotification.getCampaignId();
        val creativeId = winNotification.getCreativeId();
        val minute  = TimeUtil.roundOffToMinute(winNotification.getTimestamp());

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
