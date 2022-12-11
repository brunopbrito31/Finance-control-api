package com.brunopbrito31.financecontrolapi.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationMapperConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }


}
