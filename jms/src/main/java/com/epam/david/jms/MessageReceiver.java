package com.epam.david.jms;

import com.epam.david.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * Created by David_Chaava on 5/17/2017.
 */

@Component
public class MessageReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    private static final String ORDER_RESPONSE_QUEUE = "test-queue";

    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(final Message<Book> message) throws JMSException {
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers = message.getHeaders();
        logger.info("Application : headers received : {}", headers);
        Book response = message.getPayload();
        logger.info("Application : response received : {}", response);
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
