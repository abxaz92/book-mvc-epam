package com.epam.david.config;

import com.epam.david.camel.routes.FileIntegrationRoute;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David_Chaava on 5/18/2017.
 */
@Configuration
@ComponentScan
public class AppRouteConfiguration extends CamelConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(AppRouteConfiguration.class);
    @Autowired
    FileIntegrationRoute fileIntegrationRoute;

    @Override
    public List<RouteBuilder> routes() {
        return Arrays.asList(fileIntegrationRoute);
    }

}
