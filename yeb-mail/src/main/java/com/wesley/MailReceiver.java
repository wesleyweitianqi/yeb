package com.wesley;

import com.rabbitmq.client.Channel;
import com.wesley.pojo.Employee;
import com.wesley.pojo.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@Component
public class MailReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * send mail
     */
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel){
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        //tag#
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        HashOperations hashOperations = redisTemplate.opsForHash();

        try {
            if (hashOperations.entries("mail_log").containsKey(msgId)){
                LOGGER.error("message have been consumed============>{}",msgId);
                channel.basicAck(tag,false);
                return;
            }
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("welcome on board email");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            javaMailSender.send(msg);
            LOGGER.info("mail send successfully");
            //store id into redis
            hashOperations.put("mail_log",msgId,"OK");
            //manual acknowledge message
            channel.basicAck(tag,false);
        } catch (Exception e) {
            try {

                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                LOGGER.error("mail sent fail========>{}",e.getMessage());
            }
            LOGGER.error("mail sent fail========>{}",e.getMessage());
        }
    }
}
