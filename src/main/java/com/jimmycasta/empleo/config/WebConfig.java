package com.jimmycasta.empleo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${ruta.webconfig")
    private String rutaWebConfig;

    @Value("${ruta.hoja.vida")
    private String rutaHojaDeVida;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //registry.addResourceHandler("/imagenes/**").addResourceLocations("file:c:/imagenes/");
        registry.addResourceHandler("/imagenes/**").addResourceLocations("file:///c:/imagenes/");
        registry.addResourceHandler("/cv/**").addResourceLocations("file:///c:/archivos-cv/");
    }

}
