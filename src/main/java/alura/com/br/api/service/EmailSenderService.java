package alura.com.br.api.service;

public class EmailSenderService {
    public static void send(String recipientEmail, String subject, String body) {
        System.out.println("Simulating sending email to [" + recipientEmail + "] :");
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

