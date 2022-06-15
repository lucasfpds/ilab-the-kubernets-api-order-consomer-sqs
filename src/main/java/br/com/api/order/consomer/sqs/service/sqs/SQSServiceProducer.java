package br.com.api.order.consomer.sqs.service.sqs;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

public class SQSServiceProducer {
    public static void sendMessageProducer(String order, String code) {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResultSend(); 
        
        final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("Code", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(code).build());
        
        SendMessageSQS.sendMessage(sqsClient, createResult.queueUrl(), order, messageAttributes);        

        sqsClient.close();
    }
}