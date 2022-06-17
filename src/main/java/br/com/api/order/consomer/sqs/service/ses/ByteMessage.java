package br.com.api.order.consomer.sqs.service.ses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;
import software.amazon.awssdk.services.sqs.model.Message;

public class ByteMessage {
    public static String sendByte(MimeMessage message, Message msg) throws IOException, MessagingException {
        SesClient sesClient = Configurations.getSesClient();

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);
            ByteBuffer buf = ByteBuffer.wrap(outputStream.toByteArray());

            byte[] arr = new byte[buf.remaining()];
            buf.get(arr);

            SdkBytes data = SdkBytes.fromByteArray(arr);
            RawMessage rawMessage = RawMessage.builder()
                    .data(data)
                    .build();

            SendRawEmailRequest rawEmailRequest = SendRawEmailRequest.builder()
                    .rawMessage(rawMessage)
                    .build();

            sesClient.sendRawEmail(rawEmailRequest);

            return "E-mail enviado com sucesso!";
        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }

        return null;
    }
}
