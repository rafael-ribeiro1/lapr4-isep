package eapli.base.utils;

import eapli.base.Application;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Classe responsável por criar uma sessão a partir do ficheiro application.properties e enviar emails
 */
public class EmailHandler {
    private static final int NUM_RETRIES =3; //number of tries to send the email if send of email fails
    private static final int MILISECONDS_BETWEEN_RETRIES=1000;

    /**
     * Session responsável pelo envio do email
     */
    private Session session;
    /**
     * Email a partir do qual vão ser enviadas as mensagens
     */
    private String username;

    /**
     * Cria a session a partir das settings
     */
    public EmailHandler() {
        Properties appProps = Application.settings().getEmailProps();

        username = appProps.getProperty("email.from");
        final String password = appProps.getProperty("email.password");
        String host = appProps.getProperty("email.host");
        String port = appProps.getProperty("email.port");

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    /**
     * Envia email
     * @param dest email de destino
     * @param subject assunto da mensagem
     * @param content conteúdo da mensagem
     * @return true se o email tiver sido enviado com sucesso, falso se ocorreu algum erro
     */
    public boolean sendEmail(String dest, String subject, String content) {
        try {
            Message message = new MimeMessage ( session );
            message.setFrom ( new InternetAddress ( username ) );
            message.setRecipients ( Message.RecipientType.TO,
                    InternetAddress.parse ( dest ) );
            message.setSubject ( subject );
            message.setText ( content );
            Transport.send ( message );
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailWithRetry(String dest, String subject, String content){
        boolean result=false;
        for (int i = 0; !result && i < NUM_RETRIES; i++) {
            result=sendEmail ( dest, subject,content);
            if(!result){
                try {
                    Thread.sleep ( MILISECONDS_BETWEEN_RETRIES );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        }
        return result;
    }
}
