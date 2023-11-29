package com.wesley.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wesley.pojo.MailConstants;
import com.wesley.pojo.MailLog;
import com.wesley.service.MailLogService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    private  static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private MailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack,cause)-> {
            String msgId = data.getId();
            if (ack){
                LOGGER.info("{}========>message sent",msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",msgId));
            }else {
                LOGGER.error("{}========>message failed to send",msgId);
            }
        });
        rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingkey) -> {
            LOGGER.error("{}=======>message sent queue failed",msg.getBody());
        });
        return rabbitTemplate;

    }

    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
