package com.CeramiqueRachid.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 🧠 Chemin absolu dynamique vers le dossier temporaire
        String uploadPath = "file:" + System.getProperty("java.io.tmpdir") + "/ceramique/uploads/";
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}
