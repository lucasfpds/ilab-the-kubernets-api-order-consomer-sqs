package br.com.api.order.consomer.sqs.service.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.PurgeQueueRequest;

public class PurgeQueue {
    public static void purgeQueue() {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();

        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4-dlq")
                .build();

        PurgeQueueRequest queueRequest = PurgeQueueRequest.builder()
                .queueUrl(sqsClient.getQueueUrl(getQueueRequest).queueUrl())
                .build();

        sqsClient.purgeQueue(queueRequest);
    }
}
