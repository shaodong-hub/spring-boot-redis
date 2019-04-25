package com.github.springbootredis.config;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 12:57 2019-04-25
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
public class ConfigRedisConnectionFactory {
    @Resource
    private RedisProperties properties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration standalone = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        standalone.setDatabase(3);
        standalone.setHostName(properties.getHost());
        standalone.setPort(properties.getPort());
        if (StringUtils.isNotBlank(properties.getPassword())) {
            standalone.setPassword(properties.getPassword());
        }
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();
        LettucePoolingClientConfiguration.builder().commandTimeout(properties.getTimeout()).poolConfig(getGenericObjectPoolConfig());
        return new LettuceConnectionFactory(standalone, clientConfig);
    }

    private GenericObjectPoolConfig getGenericObjectPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(properties.getLettuce().getPool().getMaxIdle());
        config.setMaxTotal(properties.getLettuce().getPool().getMaxActive());
        config.setMinIdle(properties.getLettuce().getPool().getMinIdle());
        return config;
    }


}
