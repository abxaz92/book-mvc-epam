package com.epam.david.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by David_Chaava on 5/10/2017.
 */
@Configuration
public class PersistanceConfig {
    @Bean
    public EmbeddedDatabase getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder.setType(EmbeddedDatabaseType.DERBY)
                .addScript("/create-db.sql")
                .addScript("/insert-data.sql")
                .build();
        return dataSource;
    }
}
