package com.epam.david;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by David_Chaava on 5/5/2017.
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(SecurityConfig.class);
    }
}
