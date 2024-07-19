package com.tscode.LitWorld.config;

import com.tscode.LitWorld.implement.AdminInterceptor;
import com.tscode.LitWorld.implement.AuthInterceptor;
import com.tscode.LitWorld.implement.ClienInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    AdminInterceptor adminInterceptor;
    @Autowired
    ClienInterceptor clienInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/dashboard/**","/home/*")
                .excludePathPatterns("/static/*");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/dashboard/**");
        registry.addInterceptor(clienInterceptor)
                .addPathPatterns("/home","/hom-page/**");


    }


}
