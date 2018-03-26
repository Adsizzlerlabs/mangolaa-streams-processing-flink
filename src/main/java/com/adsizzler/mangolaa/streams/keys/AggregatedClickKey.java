package com.adsizzler.mangolaa.streams.keys;

import com.adsizzler.mangolaa.streams.domain.Click;
import com.adsizzler.mangolaa.streams.utils.TimeUtil;
import lombok.val;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple12;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 12/03/18.
 */
public class AggregatedClickKey implements KeySelector<Click, Tuple12<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String, String, String, String, String>> {

    @Override
    public Tuple12<Integer, Integer, Integer, Integer, Integer, Integer, ZonedDateTime, String, String, String, String, String> getKey(final Click click) throws Exception {
        //Key = advId, sourceId, clientId, campaignId, creativeId ,eventCode, minute, city, country, province, carrier, platform

        val advId = click.getAdvId();
        val sourceId = click.getSourceId();
        val clientId = click.getClientId();
        val campaignId = click.getCampaignId();
        val creativeId = click.getCreativeId();
        val eventCode = click.getEventCode();
        val minute  = TimeUtil.roundOffToMinute(click.getTimestamp());

        val carrier = click.getCarrier();
        val city = click.getCity();
        val country = click.getCountry();
        val province  = click.getProvince();
        val platform = click.getPlatform();

        return new Tuple12<>(
                advId,
                sourceId,
                clientId,
                campaignId,
                creativeId,
                eventCode,
                minute,
                city,
                country,
                province,
                carrier,
                platform
        );

    }
}
