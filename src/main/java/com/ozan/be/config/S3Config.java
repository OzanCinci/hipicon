package com.ozan.be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class S3Config {
  @Value("${application.aws.region}")
  private String region;

  @Value("${application.aws.credentials.access-key}")
  private String accessKey;

  @Value("${application.aws.credentials.secret-key}")
  private String secretKey;

  @Bean
  public S3Client s3Client() {
    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

    S3Configuration s3Config = S3Configuration.builder().pathStyleAccessEnabled(true).build();

    return S3Client.builder()
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
        .serviceConfiguration(s3Config)
        .build();
  }
}
