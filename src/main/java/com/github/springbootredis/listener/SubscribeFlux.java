package com.github.springbootredis.listener;


import com.github.springbootredis.pojo.Person;
import org.springframework.data.redis.connection.ReactiveSubscription.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 22:01 2019-04-25
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
public class SubscribeFlux {

    @Resource
    private ReactiveRedisMessageListenerContainer container;

    @PostConstruct
    public void init() {
        Flux<Message<String, String>> stream = container.receive(ChannelTopic.of("my-chanel"));
    }


}
