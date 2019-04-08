package com.github.springbootredis.config;

import com.github.springbootredis.listener.SubscribeChannelListener;
import com.github.springbootredis.listener.SubscribePatternListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


/**
 * Created in 16:16 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
public class ConfigTopic {

    /**
     * 一个确定的字符串
     *
     * @return ChannelTopic
     */
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("DataTest");
    }

    /**
     * 基于模式匹配
     *
     * @return PatternTopic
     */
    @Bean
    public PatternTopic patternTopic() {
        return new PatternTopic("/city/*");
    }

    /**
     * 消息监听器
     *
     * @return MessageListener
     */
    @Bean
    public MessageListener getMessageChannelListener() {
        return new SubscribeChannelListener();
    }
    /**
     * 消息监听器
     *
     * @return MessageListener
     */
    @Bean
    public MessageListener getMessagePatternListener() {
        return new SubscribePatternListener();
    }

    /**
     * MessageListenerContainer
     *
     * @param connectionFactory 连接工程
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 新增监听器
        container.addMessageListener(getMessageChannelListener(), channelTopic());
        return container;
    }


}
