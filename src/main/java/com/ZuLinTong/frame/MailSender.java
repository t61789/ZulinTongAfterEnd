package com.ZuLinTong.frame;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {

    private Session session;

    private Message message;

    private void init() throws IOException, MessagingException {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
        // 使用properties对象加载输入流
        properties.load(in);

        // 创建会话对象
        session = Session.getInstance(properties, new Authenticator() {
            // 认证信息，需要提供"用户账号","授权码"
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mailAccount"), properties.getProperty("authenticationCode"));
            }
        });
        // 是否打印出debug信息
        session.setDebug(true);

        // 创建邮件
        message = new MimeMessage(session);
        // 邮件发送者
        message.setFrom(new InternetAddress(properties.getProperty("mailAccount")));

    }

    public void SendMail(String targetAddress, String subject, String content) throws MessagingException {

        // 邮件接受者
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(targetAddress));
        // 邮件主题
        message.setSubject(subject);
        // 邮件正文
        message.setContent(content, "text/html;charset=UTF-8");

        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
