package org.example.springemailsender.infra

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsSesConfig(
    @Value("\${aws.region}")
    private val region: String,
    @Value("\${aws.accessKey}")
    private val accessKey: String,
    @Value("\${aws.secretKey}")
    private val secretKey: String,
) {
    @Bean
    fun amazonSimpleEmailService(): AmazonSimpleEmailService {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        return AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
