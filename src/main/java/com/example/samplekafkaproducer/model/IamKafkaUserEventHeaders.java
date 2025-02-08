package com.example.samplekafkaproducer.model;

import lombok.Data;

@Data
public class IamKafkaUserEventHeaders {
    private String eventId;
    private String eventType;
    private String eventCategory;
    private String eventProducer;
    private String eventVersion;
    private String eventTimestamp;
    private String contentType;
    private Long xCustomerId;
    private Integer xCustomerType;
    private Long xUserId;
    private String xShard;
}
