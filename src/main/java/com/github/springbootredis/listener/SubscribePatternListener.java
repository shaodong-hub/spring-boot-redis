package com.github.springbootredis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * <p>
 * 创建时间为 22:16 2019-04-08
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
public class SubscribePatternListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {

    }
}
