package com.epam.david.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.XmlViewResolver;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Configuration
@ComponentScan({"com.epam.david.mvc"})
public class AppConfig {
}
