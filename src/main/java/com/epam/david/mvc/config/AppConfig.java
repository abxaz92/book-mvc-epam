package com.epam.david.mvc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by David_Chaava on 5/4/2017.
 */
@Configuration
@ComponentScan({"com.epam.david.mvc"})
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableAutoConfiguration
public class AppConfig {



}
