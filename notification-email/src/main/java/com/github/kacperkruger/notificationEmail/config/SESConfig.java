package com.github.kacperkruger.notificationEmail.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class SESConfig {

    @Value("${aws.access_key}")
    private String AWS_ACCESS_KEY;

    @Value("${aws.secret_key}")
    private String AWS_SECRET_KEY;

    @Value("${aws.region}")
    private String AWS_REGION;

    @Value("${aws.verified.mail.from}")
    private String AWS_VERIFIED_MAIL_FROM;

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withRegion(AWS_REGION)
                .withCredentials(awsCredentialsProvider())
                .build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    @Bean
    public BasicAWSCredentials awsCredentials() {
        return new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
    }

    @Bean
    SimpleMailMessage awsMailTemplate() {
        SimpleMailMessage awsMailTemplate = new SimpleMailMessage();
        awsMailTemplate.setFrom(AWS_VERIFIED_MAIL_FROM);
        return awsMailTemplate;
    }

}
