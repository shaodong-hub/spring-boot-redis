package com.github.springbootredis.listener;

import com.alibaba.fastjson.JSON;
import com.github.springbootredis.pojo.Person;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;


/**
 * <p>
 * 创建时间为 20:50 2019-04-08
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

public class SubscribeChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        Person person = (Person) serializer.deserialize(message.getBody());
        System.out.println("Channel:" + new String(message.getChannel()));
        System.out.println("Body   :" + JSON.toJSONString(person));
        System.out.println("bytes  :" + new String(bytes));

    }
}
