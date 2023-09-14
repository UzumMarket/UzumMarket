package mail.base;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class BaseEmail {
    private static final BaseEmail baseEmail = new BaseEmail();
    private Session session;
    private MimeMessage message;

    private Properties properties;

    protected BaseEmail newSession(Properties properties) {
        this.properties = properties;
        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.smtp.gmail"), properties.getProperty("mail.smtp.password"));
            }
        });
        return baseEmail;
    }

    protected BaseEmail newMessage(String subject, String body, String to) {
        message = new MimeMessage(session);
        try {
            message.setText(body);
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(properties.getProperty("mail.smtp.gmail"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return baseEmail;
    }

    protected void execute() {
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static BaseEmail getInstance() {
        return baseEmail;
    }

}
