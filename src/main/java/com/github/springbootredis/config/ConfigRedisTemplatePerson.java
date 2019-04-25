package com.github.springbootredis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.springbootredis.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * Created in 15:59 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Configuration
public class ConfigRedisTemplatePerson {


    @Bean
    public ReactiveRedisTemplate<String, Person> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, Person> serializationContext = RedisSerializationContext
                .<String, Person>newSerializationContext(new FastJsonRedisSerializer<>(Person.class))
                .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }


//    @Bean
//    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl().and()
//                .commandTimeout(Duration.ofSeconds(2))
//                .shutdownTimeout(Duration.ZERO)
//                .build();
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379), clientConfig);
//    }

}
