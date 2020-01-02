package com.github.springbootredis.config;

import io.lettuce.core.ReadFrom;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * <p>
 * 创建时间为 22:14 2019-04-25
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
//@Configuration
public class ConfigRedisConnectionFactory {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration
                .builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

//        LettucePoolingClientConfiguration.builder().poolConfig()


        RedisClusterConfiguration configuration = new RedisClusterConfiguration();


        return new LettuceConnectionFactory(configuration, clientConfig);
    }


}
