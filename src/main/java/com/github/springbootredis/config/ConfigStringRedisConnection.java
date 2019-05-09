package com.github.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created in 15:55 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Configuration
public class ConfigStringRedisConnection {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取 StringRedisConnection 对象, 用于 Redis Lua 操作
     *
     * @return StringRedisConnection
     */
    @Bean
    public StringRedisConnection getStringRedisConnection() {
        final AtomicReference<StringRedisConnection> redisConnection = new AtomicReference<>();
        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            redisConnection.set((StringRedisConnection) connection);
            return null;
        });
        return redisConnection.get();
    }


}
