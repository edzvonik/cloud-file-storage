package com.dzvonik.cloud.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${spring.minio.url}")
    private String url;

    @Value("${spring.minio.username}")
    private String accessKey;

    @Value("${spring.minio.password}")
    private String secretKey;

    @Value("${spring.minio.port}")
    private int port;

    @Bean
    public MinioClient getMinioClient(){
        return  MinioClient.builder()
                .endpoint(url, port, false)
                .credentials(accessKey, secretKey)
                .build();
    }

}
