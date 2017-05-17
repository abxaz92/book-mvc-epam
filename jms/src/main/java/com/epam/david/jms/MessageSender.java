package com.epam.david.jms;

import com.epam.david.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Created by David_Chaava on 5/17/2017.
 */
@Component
public class MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Book book) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.warn("start sending");
                ObjectMessage objectMessage = session.createObjectMessage(book);
                return objectMessage;
            }
        });
    }
}
