package com.github.springbootredis.schedule;

import com.github.springbootredis.pojo.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

import static com.github.springbootredis.common.PersonFactory.getPerson;

/**
 * <p>
 * 创建时间为 18:14 2019-04-12
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
//@Component
//@EnableScheduling
public class ScheduleRedisTemplateSaveTask {

    @Resource(name = "RedisTemplateFastJson")
    private RedisTemplate<String, Person> redisTemplate;

    @Scheduled(fixedRate = 1000)
    public void task() {
        redisTemplate.opsForValue().set(System.currentTimeMillis() + "", getPerson());

    }

}
