package com.github.springbootredis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.springbootredis.pojo.Person;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 15:57 2019-04-26
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class ConfigRedisTemplate {

    @Resource(name = "Factory6")
    private RedisConnectionFactory connectionFactory6;

    @Resource(name = "Factory8")
    private RedisConnectionFactory connectionFactory8;

    @Bean("RedisTemplateFastJson6")
    public RedisTemplate<String, Person> getRedisTemplate6() {
        return getRedisTemplate(connectionFactory6);
    }

    @Bean("RedisTemplateFastJson8")
    public RedisTemplate<String, Person> getRedisTemplate8() {
        return getRedisTemplate(connectionFactory8);
    }

    @NotNull
    @Contract(pure = true)
    private RedisTemplate<String, Person> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<>(Person.class));
        template.afterPropertiesSet();
        return template;
    }


}
