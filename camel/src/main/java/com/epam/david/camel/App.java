package com.epam.david.camel;

import com.epam.david.camel.routes.FileIntegrationRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by David_Chaava on 5/17/2017.
 */
public class App {
    public static void main(String[] args) {
        try {
            CamelContext camelContext = new DefaultCamelContext();
            camelContext.addRoutes(new FileIntegrationRoute());
            camelContext.start();
            Thread.sleep(30000);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
