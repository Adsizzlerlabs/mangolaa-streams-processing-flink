package in.ankushs.sample.flink.keys;

import in.ankushs.sample.flink.domain.BidReq;
import in.ankushs.sample.flink.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple4;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 23/02/18.
 */
public class AggregatedBidReqKey implements KeySelector<BidReq, Tuple4<Integer, Integer, Integer, ZonedDateTime>> {

    @Override
    public Tuple4<Integer, Integer, Integer, ZonedDateTime> getKey(final BidReq bidReq) throws Exception {
        val advId = bidReq.getAdvId();
        val sourceId = bidReq.getSourceId();
        val clientId = bidReq.getClientId();
        val minute = TimeUtil.roundOffToMinute(bidReq.getTimestamp());
        //Key = advId, sourceId, clientId, timestamp

        return new Tuple4<>(
                advId,
                sourceId,
                clientId,
                minute
        );
    }
}
