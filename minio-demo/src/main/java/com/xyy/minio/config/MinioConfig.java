package com.xyy.minio.config;

import com.xyy.minio.entity.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yayu
 * @title: MinioConfig
 * @description: minio 配置类
 * @date 2022/4/18 16:02
 */
@Configuration
public class MinioConfig {
    @Autowired
    private MinioProperties minioProperties;
    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioProperties.getEndpoint())
                        .credentials(minioProperties.getAccessKey(),
                                minioProperties.getSecretKey())
                        .build();
        return minioClient;
    }
}
