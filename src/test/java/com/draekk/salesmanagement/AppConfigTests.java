package com.draekk.salesmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.draekk.salesmanagement.models.DtoManager;

@Configuration
public class AppConfigTests {

    @Bean
    public DtoManager dtoManager() {
        return new DtoManager() {
            
        };
    }
}
