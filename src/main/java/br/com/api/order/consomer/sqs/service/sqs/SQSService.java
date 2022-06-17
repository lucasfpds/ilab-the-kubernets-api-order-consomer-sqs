package br.com.api.order.consomer.sqs.service.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.order.consomer.sqs.dto.OrderDTO;
import br.com.api.order.consomer.sqs.service.ses.SESService;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;

public class SQSService {
    public static List<Integer> messageService(List contadores) throws Exception {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = CreateResultReceive.getCreateResult();

        Integer count = (Integer) contadores.get(0);
        Integer countDlq = (Integer) contadores.get(1);

        try {
            List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());

            OrderDTO jsonPedido = new OrderDTO();

            for (Message msg : messages) {
                String stringMessage = msg.body();

                jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);

                if (jsonPedido.getStatus().equals("aberto")) {
                    jsonPedido.setStatus("concluído");
                    count++;

                    String statusEmail = SESService.sendMessage("🚩 Status do Pedido 🚩", 
                                                                jsonPedido.getNameUser(), 
                                                                jsonPedido.getEmailUser(),
                                                                jsonPedido.getDescription(),
                                                                msg);

                    if (statusEmail == "Ok. E-mail enviado!") {
                        jsonPedido.setStatusEmail("enviado");

                        String stringPedidoOut = new Gson().toJson(jsonPedido);
                        SQSServiceProducer.sendMessageProducer(stringPedidoOut, jsonPedido.getIdAdmin().toString());
                        DeleteMessage.deleteMessages(sqsClient, createResult.queueUrl(), msg);

                        count = 0;
                    }
                }
            }

            if (!messages.isEmpty() && count == 3) {
                if(countDlq == 250) {                  
                    PurgeQueue.purgeQueue();
                    countDlq = 0;
                }

                String stringPedidoOut = new Gson().toJson(jsonPedido);
                SQSServiceProducer.sendMessageProducer(stringPedidoOut,
                        jsonPedido.getIdAdmin().toString());

                count = 0;
                countDlq++;
            }

            
            contadores.set(0, count);
            contadores.set(1, countDlq);
            
            sqsClient.close();

            return contadores;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}