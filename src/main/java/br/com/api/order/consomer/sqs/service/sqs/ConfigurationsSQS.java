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

    public static GetQueueUrlRequest getUrlRequest(String nomeFila) {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName(nomeFila).build();
        return request;
    }
}
