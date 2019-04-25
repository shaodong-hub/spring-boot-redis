package com.github.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 21:58 2019-04-25
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
public class ConfigMessageListenerContainer {

    @Resource
    private ReactiveRedisConnectionFactory factory;

    @Bean
    public ReactiveRedisMessageListenerContainer getReactiveRedisMessageListenerContainer(){
        return new ReactiveRedisMessageListenerContainer(factory);
    }


}
