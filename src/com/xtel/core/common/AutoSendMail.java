package com.xtel.core.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

import static com.xtel.core.common.InfoConfigEmail.*;

public class AutoSendMail {

    public static void sendMail(String receiveMail, String content, String title) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        // Dia chi may chu SMTP gmail
        properties.put("mail.smtp.host", HOST_NAME);
        // Cong SMTP gmail (TLS - 587 ; SSL 465)
        properties.put("mail.smtp.port", TSL_PORT);

        String myAccountEmail = APP_EMAIL;
        String password = APP_PASSWORD;

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = infoMessage(session, myAccountEmail, receiveMail, content, title);

        Transport.send(message);
    }

    public static Message infoMessage(Session session, String myAccountEmail, String receiveMail, String content, String title){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
            message.setSubject(title);
//            message.setText(content);
            message.setContent(content,"text/html");
            return message;
        }
        catch (Exception ex){
            Logger.getLogger(AutoSendMail.class.getName());
        }
        return  null;
    }


}
