package br.com.api.order.consomer.sqs.service.ses;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sqs.model.Message;

public class SESService {
    public static String sendMessage(String subject, String user, Message msg) {
        SesClient sesClient = Configurations.getSesClient();

        String bodyText = "O status do seu pedido foi atualizado.";

        String bodyHTML = "<html>"
                + "<head></head>"
                + "<body>"
                + "<h1>Olá " + user + "! Tudo bom? </h1>"
                + "<p> Temos uma ótima notícia para você. O seu pedido foi realizado com sucesso!</p>"
                + "</body>"
                + "</html>";

        try {
            MimeMessage messageMime = SendMessage.send(sesClient, "thekubernetes4@gmail.com", 
                                                        user, subject, bodyText, bodyHTML);

            String statusEmail = ByteMessage.sendByte(messageMime, msg);

            if (statusEmail == "E-mail enviado com sucesso!") {
                sesClient.close();
                return "Ok. E-mail enviado!";
            }

            sesClient.close();

        } catch (IOException | MessagingException e) {
            e.getStackTrace();
        }

        return null;
    }
}
