package com.bootproj.pmcweb.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

public class FileUploadConfig {
    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver
                = new org.springframework.web.multipart.commons.CommonsMultipartResolver();

        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
        return multipartResolver;
    }
}
