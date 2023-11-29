package com.wesley;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VoaMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoaMailApplication.class, args);
    }
    @Bean
    public Queue queue(){
        return new Queue("mail.welcome");
    }
}
