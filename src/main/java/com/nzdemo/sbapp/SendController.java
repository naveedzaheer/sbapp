package com.nzdemo.sbapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    private static final String DESTINATION_NAME = "nzjms2q";

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/messages")
    public Greeting postMessage(@RequestBody Greeting greeting) {
        logger.info("Sending message");
        jmsTemplate.convertAndSend(DESTINATION_NAME, greeting.toString());
        return greeting;
    }

    @GetMapping("/messages")
	public String getMessage() {
        logger.info("Receiving message");
        String greeting = (String)jmsTemplate.receiveAndConvert(DESTINATION_NAME);
        return greeting;
    }
}