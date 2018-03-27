package com.adsizzler.mangolaa.streams.aggregations;

import com.adsizzler.mangolaa.streams.jackson.serializers.UUIDSerializer;
import com.adsizzler.mangolaa.streams.jackson.serializers.ZonedDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by ankushsharma on 12/03/18.
 */
@Data
@Builder
public class AggregatedClick {

    @JsonProperty(value = "uuid", required = true)
    @JsonSerialize(using = UUIDSerializer.class)
    private final UUID uuid;

    @JsonProperty(value = "advId", required = true)
    private final Integer advId;

    @JsonProperty(value = "clientId", required = true)
    private final Integer clientId;

    @JsonProperty(value = "sourceId", required = true)
    private final Integer sourceId;

    @JsonProperty(value = "campaignId", required = true)
    private final Integer campaignId;

    @JsonProperty(value = "creativeId", required = true)
    private final Integer creativeId;

    @JsonProperty(value = "count", required = true)
    private final Integer count;

    @JsonProperty(value = "timestamp", required = true)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private final ZonedDateTime timestamp;

    @JsonProperty(value = "eventCode", required = true)
    private final Integer eventCode;

    @JsonProperty(value = "city", required = true)
    private final String city;

    @JsonProperty(value = "country", required = true)
    private final String country;

    @JsonProperty(value = "province", required = true)
    private final String province;

    @JsonProperty(value = "platform", required = true)
    private final String platform;

    @JsonProperty(value = "carrier", required = true)
    private final String carrier;

}
