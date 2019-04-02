package com.github.springbootredis.listener;

import org.springframework.stereotype.Component;

/**
 * Created in 16:21 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Component
public class ReceivingMessages{

    public void receiveMessage(String message){
        System.out.println(message);
    }

}
