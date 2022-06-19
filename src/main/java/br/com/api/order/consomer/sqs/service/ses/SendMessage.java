package br.com.api.order.consomer.sqs.service.ses;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import software.amazon.awssdk.services.ses.SesClient;

public class SendMessage {    

    public static MimeMessage send(SesClient client,
            String sender,
            String recipient,
            String subject,
            // String bodyText,
            String bodyHTML) throws AddressException, MessagingException, IOException {

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setSubject(subject, "UTF-8");
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        // MimeBodyPart textPart = new MimeBodyPart();
        // textPart.setContent(bodyText, "text/plain; charset=UTF-8");

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(bodyHTML, "text/html; charset=UTF-8");

        MimeMultipart msgBody = new MimeMultipart();
        // msgBody.addBodyPart(textPart);
        msgBody.addBodyPart(htmlPart);

        message.setContent(msgBody);

        return message;
    }
}