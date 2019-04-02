package com.github.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;


/**
 *
 *
 * Created in 16:16 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
public class ConfigTopic {

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("DataTest");
    }

}
