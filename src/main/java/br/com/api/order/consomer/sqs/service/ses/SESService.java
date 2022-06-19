package br.com.api.order.consomer.sqs.service.ses;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sqs.model.Message;

public class SESService {
    public static String sendMessage(String subject, String html, String emailUser, String description, Message msg) {
        SesClient sesClient = Configurations.getSesClient();
       
        String bodyHtml = html;

        try {
            MimeMessage messageMime = SendMessage.send(sesClient, "thekubernetes4@gmail.com", 
                                                        emailUser, subject, bodyHtml);

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
