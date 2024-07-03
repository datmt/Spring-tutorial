package com.datmt.tools.servingstaticfiles;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class Config implements WebMvcConfigurer  {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapping URL path to the external directory
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/var/www/static/images/")
        ;
        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:/var/www/static/videos/");
    }
}
