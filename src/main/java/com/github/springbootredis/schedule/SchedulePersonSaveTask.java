package com.github.springbootredis.schedule;

import com.github.springbootredis.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.github.springbootredis.common.PersonFactory.getPerson;

/**
 * <p>
 * 创建时间为 10:47 2019-04-26
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component
@EnableScheduling
public class SchedulePersonSaveTask {

    @Resource(name = "RedisTemplateFastJson6")
    private RedisTemplate<String, Person> redisTemplate6;

    @Resource(name = "RedisTemplateFastJson8")
    private RedisTemplate<String, Person> redisTemplate8;


    @Scheduled(fixedRate = 1000)
    public void task() {
        Person person = getPerson();
        redisTemplate6.opsForHash().put("Person", person.getId(), person);
        redisTemplate8.opsForHash().put("Person", person.getId(), person);
        log.info(person.toString());
    }

}
