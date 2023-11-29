package com.wesley;

import com.wesley.pojo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;


public class MailReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * send mail
     */
    @RabbitListener(queues = "mail.welcome")
    public void handler(Employee employee){
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try{
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("welcome on board");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posname", employee.getPosition().getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            //send mail
            javaMailSender.send(msg);
        }catch (MessagingException e){
            LOGGER.error("邮件发送失败=====>{}", e.getMessage());
        }
    }
}
