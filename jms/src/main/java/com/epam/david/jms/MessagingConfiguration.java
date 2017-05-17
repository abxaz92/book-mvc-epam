package com.epam.david.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

/**
 * Created by David_Chaava on 5/17/2017.
 */

@Configuration
public class MessagingConfiguration {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private static final String ORDER_QUEUE = "test-queue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("com.epam.david", "java.lang"));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(ORDER_QUEUE);
        return template;
    }


}
