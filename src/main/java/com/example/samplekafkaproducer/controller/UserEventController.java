package com.example.samplekafkaproducer.controller;

import com.example.samplekafkaproducer.helper.IamKafkaUserEventHelper;
import com.example.samplekafkaproducer.model.UserEventApiRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/produce")
public class UserEventController {

    @Autowired
    private IamKafkaUserEventHelper iamKafkaUserEventHelper;

    @PostMapping("/user-event/create-user")
    public ResponseEntity<String> createUserEvent(@RequestBody UserEventApiRequestBody userEventApiRequestBody) {
        // iamKafkaUserEventHelper.publish();
        log.info("Received user event: {}", userEventApiRequestBody.getIamKafkaUserEvent());
        log.info("Received user event headers: {}", userEventApiRequestBody.getIamKafkaUserEventHeaders());
        iamKafkaUserEventHelper.publish(userEventApiRequestBody.getIamKafkaUserEventHeaders(), userEventApiRequestBody.getIamKafkaUserEvent());
        return ResponseEntity.ok("User event received");
    }



}
