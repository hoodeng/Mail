package com.tengteng.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    static String QQMail = "xxxxx@qq.com";
    static String PSW = "xxx";
    public static void main(String[] args){
        System.out.println("hello mail");
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        try {
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(QQMail));
            // 设置收件人邮箱地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(QQMail)});
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
            // 设置邮件标题
            message.setSubject("QQ 发送测试");
            // 设置邮件内容
            message.setText("QQ 发送测试内容");
            // 得到邮差对象
            Transport transport = session.getTransport();
            // 连接自己的邮箱账户
            transport.connect(QQMail, PSW);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
