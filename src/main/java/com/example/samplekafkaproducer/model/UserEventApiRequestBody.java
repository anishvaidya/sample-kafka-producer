package com.example.samplekafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserEventApiRequestBody {

    @JsonProperty("iam_kafka_user_event")
    private IamKafkaUserEvent iamKafkaUserEvent;

    @JsonProperty("iam_kafka_user_event_headers")
    private IamKafkaUserEventHeaders iamKafkaUserEventHeaders;

}
