package com.bootproj.pmcweb.Config;

import com.bootproj.pmcweb.Common.Intercepter.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    final private Environment env;
    final LoggingInterceptor loggingInterceptor;
    private final String PROFILE_PATH_PROPERTY = "profile.image.path";
    private final String LOCAL_WINDOW = "C:";
    private final String LINUX_MAC = "/";
    private final String OUT_IMAGE =  "/out/img/";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // security쪽에서 래핑하는 것과 충돌이 나서 /user/**도 제외함.
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/vendor/**", "/css/*", "/img/*", "/alarm/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (env.getProperty(PROFILE_PATH_PROPERTY).startsWith(LOCAL_WINDOW)){
            registry.addResourceHandler(OUT_IMAGE + "**")
                    .addResourceLocations("file:///");
        } else if (env.getProperty(PROFILE_PATH_PROPERTY).startsWith(LINUX_MAC)){
            registry.addResourceHandler(OUT_IMAGE + "**")
                    .addResourceLocations("file:");
        }

    }
}
