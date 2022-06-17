package br.com.api.order.consomer.sqs.service.sqs;

import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

public class CreateResultSend {
    static GetQueueUrlRequest request = ConfigurationsSQS.getUrlRequest("queue-grupo4-order");

    public static GetQueueUrlResponse getCreateResult() {
        GetQueueUrlResponse createResult = ConfigurationsSQS.getSqsClient().getQueueUrl(request);

        return createResult;
    }
}
