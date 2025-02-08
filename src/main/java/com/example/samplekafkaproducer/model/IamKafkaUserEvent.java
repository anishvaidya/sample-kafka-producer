package com.example.samplekafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IamKafkaUserEvent {
    private String version;
    private String type;
    private String id;
    private String timestamp;
    @JsonProperty("user_id")
    private Long userId;
    private String username;
    @JsonProperty("customer_id")
    private Long customerId;
    @JsonProperty("customer_name")
    private String customerName;
    private Object before;
    private Object after;
}

