package com.bupt.uta.utils;

import com.bupt.uta.entity.Customer;
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmail {
    public static void send(Customer account, String code) throws GeneralSecurityException {
        // 收件人电子邮箱
        String to = account.getEmail();

        // 发件人电子邮箱
        String from = "1027868826@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1027868826@qq.com", "akhdnhvdpxetbcci"); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("Congratulations on your registration");

            // 设置消息体
            message.setText("Your email is "+account.getEmail()+" and your verification code is "+code+
                    ". Thank you for join us.");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from qq.com");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

//    public static void announce(Customer account,EventProposal eventProposal) throws GeneralSecurityException{
//        // 收件人电子邮箱
//        String to = account.getEmail();
//
//        // 发件人电子邮箱
//        String from = "1027868826@qq.com";
//
//        // 指定发送邮件的主机为 smtp.qq.com
//        String host = "smtp.qq.com";  //QQ 邮件服务器
//
//        // 获取系统属性
//        Properties properties = System.getProperties();
//
//        // 设置邮件服务器
//        properties.setProperty("mail.smtp.host", host);
//
//        properties.put("mail.smtp.auth", "true");
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.ssl.socketFactory", sf);
//        // 获取默认session对象
//        Session session = Session.getDefaultInstance(properties,new Authenticator(){
//            public PasswordAuthentication getPasswordAuthentication()
//            {
//                return new PasswordAuthentication("1027868826@qq.com", "akhdnhvdpxetbcci"); //发件人邮件用户名、密码
//            }
//        });
//
//        try{
//            // 创建默认的 MimeMessage 对象
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: 头部头字段
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: 头部头字段
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: 头部头字段
//            message.setSubject("Your event proposal submitted successfully!");
//
//            // 设置消息体
//            message.setText("We have received your payment and will speed up the review of the event proposal！\n"+
//                    "The details of your event proposal are as follows. If there is any mistake, please contact us as soon as possible.\n"+
//                    "Event Proposal name: "+eventProposal.getEventName()+"\n"+
//                    "Event Proposal Status: "+eventProposal.getEventStatus()+"\n"+
//                    "Payment Amount: "+eventProposal.getPaymentAmount()+"\n"+
//                    "Pay Time: "+eventProposal.getPaytime()+"\n"+
//                    "Have a good day!");
//
//            // 发送消息
//            Transport.send(message);
//            System.out.println("Sent message successfully....from qq.com");
//        }catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
}
