package com.adsizzler.mangolaa.streams.domain;

import com.adsizzler.mangolaa.streams.jackson.deserializers.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * Created by ankushsharma on 26/03/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class Conversion {

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

    @JsonProperty(value = "eventCode", required = true)
    private final Integer eventCode;

    @JsonProperty(value = "timestamp", required = true)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private final ZonedDateTime timestamp;

}
