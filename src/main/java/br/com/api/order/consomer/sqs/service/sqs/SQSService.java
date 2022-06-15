package br.com.api.order.consomer.sqs.service.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.order.consomer.sqs.dto.OrderDTO;
import br.com.api.order.consomer.sqs.service.ses.SESService;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;

public class SQSService {
    public static Integer messageService(Integer count) {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResultReceive();

        List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());

        OrderDTO jsonPedido = new OrderDTO();

        for (Message msg : messages) {
            String stringMessage = msg.body();

            jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);
    
            if (jsonPedido.getStatus().equals("aberto")) {
                String statusEmail = SESService.sendMessage("🚩 Status do Pedido 🚩", jsonPedido.getEmailUser(), msg);
                
                if(statusEmail == "Ok. E-mail enviado!") {
                    jsonPedido.setStatusEmail("enviado");
                    jsonPedido.setStatus("concluído");                       
                    
                    String stringPedidoOut = new Gson().toJson(jsonPedido);
                    SendMessageSQS.sendMessage(sqsClient, 
                            ConfigurationsSQS.getCreateResultSend().queueUrl(), 
                            stringPedidoOut);
                }

                count++;
            }
        }

        if(!messages.isEmpty() && count == 3) {
            String stringPedidoOut = new Gson().toJson(jsonPedido);
            SendMessageSQS.sendMessage(sqsClient, ConfigurationsSQS.getCreateResultSend().queueUrl(), stringPedidoOut);
        }
    
        sqsClient.close();

        return count;
    }
}