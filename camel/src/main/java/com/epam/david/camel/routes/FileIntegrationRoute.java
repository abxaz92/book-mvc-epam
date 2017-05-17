package com.epam.david.camel.routes;

import com.epam.david.camel.beans.TransformationBean;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by David_Chaava on 5/17/2017.
 */
public class FileIntegrationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C:\\Users\\David_Chaava\\IdeaProjects\\camel\\in")
                .bean(new TransformationBean(), "makeUpperCase")
                .to("file:C:\\Users\\David_Chaava\\IdeaProjects\\camel\\out");
    }
}
