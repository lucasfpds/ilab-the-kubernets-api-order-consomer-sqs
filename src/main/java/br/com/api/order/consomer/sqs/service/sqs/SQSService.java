package br.com.api.order.consomer.sqs.service.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.order.consomer.sqs.dto.OrderDTO;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;


public class SQSService {
    public static void messageService() {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResult();

        List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());

        for (Message msg : messages) {
            String stringMessage = msg.body();
            DeleteMessage.deleteMessages(sqsClient, createResult.queueUrl(), msg);

            OrderDTO jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);

            if (jsonPedido.getStatus().equals("aberto")) {
                jsonPedido.setStatus("concluído");

                String stringPedidoOut = new Gson().toJson(jsonPedido);
                SendMessage.sendMessage(sqsClient, createResult.queueUrl(), stringPedidoOut);
            }
        }
        
        sqsClient.close();
    }
}