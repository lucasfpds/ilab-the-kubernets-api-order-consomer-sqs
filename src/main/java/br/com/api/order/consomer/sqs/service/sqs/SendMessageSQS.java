package br.com.api.order.consomer.sqs.service.sqs;

import java.util.Map;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SendMessageSQS {
    public static void sendMessage(SqsClient sqsClient, String queueUrl, String message,
            Map<String, MessageAttributeValue> messageAttributes) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .messageAttributes(messageAttributes)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}
