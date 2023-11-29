package com.wesley.pojo;

public class MailConstants {
    //message sending
    public static final Integer DELIVERING = 0;

    //message sending succeed
    public static final Integer SUCCESS = 1;

    //message sending fail
    public static final Integer FAILURE = 2;

    //max resend
    public static final Integer MAX_TRY_COUNT = 3;

    //message timeout
    public static final Integer MSG_TIMEOUT = 1;

    //queue
    public static final String MAIL_QUEUE_NAME = "mail.queue";

    //exchange
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";

    //routing key
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key";
}
