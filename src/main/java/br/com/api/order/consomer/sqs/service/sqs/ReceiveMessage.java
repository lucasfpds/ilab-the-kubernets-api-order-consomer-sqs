package br.com.api.order.consomer.sqs.service.sqs;

import java.util.List;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.*;

public class ReceiveMessage {
    SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
    GetQueueUrlResponse createResult = CreateResultReceive.getCreateResult();

    public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(5)
                .maxNumberOfMessages(5)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        return messages;
    }
}
