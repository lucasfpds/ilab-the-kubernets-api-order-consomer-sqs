package br.com.api.order.consomer.sqs.SESTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.api.order.consomer.sqs.service.ses.SendMessage;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SESTest {
    
    private static SesClient client ;
    private static String sender="";
    private static String recipient="";
    private static String subject="";
    


    private static String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
    + "<p>Nome: " + "Zé das Coves.</p>" + "</body>" + "</html>";


    @BeforeAll
    public static void setUp() throws IOException, URISyntaxException {

        client = SesClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        try (InputStream input = SESTest.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Populate the data members required for all tests
            sender = prop.getProperty("sender");
            recipient = prop.getProperty("recipient");
            subject = prop.getProperty("subject");
            


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNull() {
        assertNull(client);
        System.out.println("Test 1 passed");
    }


    @Test
    @Order(2)
    public void SendMessage() {

        try {
    
            SendMessage.send(client, sender,recipient, subject,bodyHTML);
            System.out.println("Test 2 passed");

        } catch (IOException | MessagingException e) {
            e.getStackTrace();
        }
    }

    @Test
    @Order(4)
    public void ListIdentities() {
       // ListIdentities.listSESIdentities(client);
        System.out.println("Test 4 passed");
    }
}
