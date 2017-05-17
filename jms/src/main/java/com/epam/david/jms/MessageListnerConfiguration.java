package com.epam.david.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * Created by David_Chaava on 5/17/2017.
 */

@Configuration
@EnableJms
public class MessageListnerConfiguration {
    @Autowired
    private ConnectionFactory connectionFactory;

    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        return factory;
    }
}
