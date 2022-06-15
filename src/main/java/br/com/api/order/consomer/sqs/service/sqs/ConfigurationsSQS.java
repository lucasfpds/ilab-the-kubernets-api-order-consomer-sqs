package br.com.api.order.consomer.sqs.service.sqs;

import br.com.api.order.consomer.sqs.service.Authentication;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

public class ConfigurationsSQS {
    public static SqsClient getSqsClient() { 
        
        SqsClient sqsClient = SqsClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(Authentication.getCredentials())
            .build();
        
        return sqsClient;
    }

    static GetQueueUrlRequest getUrlRequestSend() {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4-order").build();
        return request;
    }

    static GetQueueUrlRequest getUrlRequestReceive() {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4").build();
        return request;
    }

    public static GetQueueUrlResponse getCreateResultSend() {
        GetQueueUrlResponse createResult = getSqsClient().getQueueUrl(getUrlRequestSend());

        return createResult;
    }

    public static GetQueueUrlResponse getCreateResultReceive() {
        GetQueueUrlResponse createResult = getSqsClient().getQueueUrl(getUrlRequestReceive());

        return createResult;
    }
}
