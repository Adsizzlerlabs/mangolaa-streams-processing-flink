# About

Welcome. This is the stream processing module for Mangolaa platform. We stream events (BidReq, BidResp, Wins etc) from Apache Kafka. Data is aggregated(counted) every minute using Apache Flink's DataStream API. Aggregated Counts are then pushed to Kafka, and saved to MySQL via a [Kafka Consumer](https://github.com/Adsizzlerlabs/mangolaa-kafkaconsumer-aggregations)


## BidRequest

Data format : JSON string compressed using gzip compression

Key : AdvId, Source Id(id of the SSP or publisher), clientId (the id of the client in the system), minute( the timestamp of the bid req rounded off to the minute).

Aggregated Object :

1. adv Id : int
2. client Id : int
3. minute : ZonedDateTime
4. count : int 
5. sourceId : int



## BidResponse

Data format : JSON string compressed using gzip compression

Key : AdvId, Source Id(id of the SSP or publisher), clientId (the id of the client in the system), campaignId, creativeId, minute( the timestamp of the bid req rounded off to the minute).

Aggregated Object :

1. adv Id : int
2. client Id : int
3. minute : ZonedDateTime
4. count : int 
5. sourceId : int
6. campaignId : int
7. creativeId : int

## WinNotifications

Data format : JSON string compressed using gzip compression

Key : AdvId, Source Id(id of the SSP or publisher), clientId (the id of the client in the system), campaignId, creativeId, minute( the timestamp of the bid req rounded off to the minute).

Aggregated Object :

1. adv Id : int
2. client Id : int
3. minute : ZonedDateTime
4. count : int 
5. sourceId : int
6. campaignId : int
7. creativeId : int


## Impressions and Billing

Data format : JSON string compressed using gzip compression

Key : AdvId, Source Id(id of the SSP or publisher), clientId (the id of the client in the system), campaignId, creativeId, minute( the timestamp of the bid req rounded off to the minute).

Aggregated Object :

1. adv Id : int
2. client Id : int
3. minute : ZonedDateTime
4. count : int 
5. sourceId : int
6. campaignId : int
7. creativeId : int