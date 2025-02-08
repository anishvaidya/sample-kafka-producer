package com.example.samplekafkaproducer.helper;


import com.example.samplekafkaproducer.model.IamKafkaUserEvent;
import com.example.samplekafkaproducer.model.IamKafkaUserEventHeaders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class IamKafkaUserEventHelper {

    @Autowired
    private KafkaTemplate<String,String> template;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("iam.user.events")
    private String userEventTopic;

    public void publish(final @NonNull IamKafkaUserEventHeaders headers, final @NonNull IamKafkaUserEvent event) {

        try {
            List<Header> kafkaHeaders = toHeaders(headers);
            String key = event.getCustomerId() != null ? event.getCustomerId().toString() : null;
            var myEvent = new ProducerRecord<String, String>(userEventTopic, null, key, objectMapper.writeValueAsString(event), kafkaHeaders);
            log.info("Publishing event to the IAM Kafka: {}", myEvent);
            template.send(myEvent);
        } catch (JsonProcessingException e) {
            log.error("An error occurred while publishing an event to the IAM Kafka.", e);
        }

    }

    private List<Header> toHeaders(final @NonNull IamKafkaUserEventHeaders headers) {
        List<Header> kafkaHeaders = new LinkedList<>();
        try {
            Field[] fields = headers.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = convertFieldName(field.getName());
                Object value = field.get(headers);
                if (value != null) {
                    kafkaHeaders.add(new RecordHeader(name, value.toString().getBytes()));
                }
            }
        } catch (IllegalAccessException e) {
            log.error("An error occurred while converting headers to Kafka headers.", e);
        }
        return kafkaHeaders;
    }

    private String convertFieldName(final @NonNull String fieldName) {
        String[] strArray = fieldName.split("(?=\\p{Lu})");
        if (strArray != null && strArray.length > 0) {
            strArray[0] = StringUtils.capitalize(strArray[0]);
            return String.join("-", strArray);
        }
        return fieldName;
    }

}
