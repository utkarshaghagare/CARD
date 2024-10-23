package com.project.card.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class AmazonS3Config {

    @Value("${do.spaces.access-key}")
    private String id;
    @Value("${do.space.secret-key}")
    private String key;
    @Value("${do.space.endpoint}")
    private String link;

    @Value("${do.space.region}")
    private Region region;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(region) // This is required but will be ignored by the custom endpoint
                .endpointOverride(URI.create(link))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        id,key)))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }
}