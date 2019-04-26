package com.github.springbootredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

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

    @Autowired
    private RedisConnectionFactory factory;

//    @Resource(name = "Factory2")
//    private RedisConnectionFactory connectionFactory2;
//
//    @Resource(name = "Factory6")
//    private RedisConnectionFactory connectionFactory6;
//
//    @Resource(name = "Factory8")
//    private RedisConnectionFactory connectionFactory8;
//
//    @Bean("RedisTemplateFastJson2")
//    public RedisTemplate<String, String> getRedisTemplate2() {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory2);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
//
//
//    @Bean("RedisTemplateFastJson6")
//    public RedisTemplate<String, Person> getRedisTemplate6() {
//        return getRedisTemplate(connectionFactory6);
//    }
//
//    @Bean("RedisTemplateFastJson8")
//    public RedisTemplate<String, Person> getRedisTemplate8() {
//        return getRedisTemplate(connectionFactory8);
//    }
//
//    @NotNull
//    @Contract(pure = true)
//    private RedisTemplate<String, Person> getRedisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Person> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new FastJsonRedisSerializer<>(Person.class));
//        template.afterPropertiesSet();
//        return template;
//    }
//

}
