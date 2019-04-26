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

    @Resource
    private RedisConnectionFactory factory;

    @Resource(name = "Factory1")
    private RedisConnectionFactory connectionFactory1;

    @Resource(name = "Factory2")
    private RedisConnectionFactory connectionFactory2;

    @Resource(name = "Factory3")
    private RedisConnectionFactory connectionFactory3;

    @Bean("RedisTemplateFastJson1")
    public RedisTemplate<String, String> getRedisTemplate2() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory1);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }


    @Bean("RedisTemplateFastJson2")
    public RedisTemplate<String, Person> getRedisTemplate6() {
        return getRedisTemplate(connectionFactory2);
    }

    @Bean("RedisTemplateFastJson3")
    public RedisTemplate<String, Person> getRedisTemplate8() {
        return getRedisTemplate(connectionFactory3);
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
