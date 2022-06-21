package br.com.api.order.consomer.sqs.service.sqs;

import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

public class CreateResultReceive {
    static GetQueueUrlRequest request = ConfigurationsSQS.getUrlRequest("queue-grupo4");

    public static GetQueueUrlResponse getCreateResult() {
        GetQueueUrlResponse createResult = ConfigurationsSQS.getSqsClient().getQueueUrl(request);

        return createResult;
    }
}
