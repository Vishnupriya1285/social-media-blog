package com.learning.springboot.socialmedia_blog_app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfiguration {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
