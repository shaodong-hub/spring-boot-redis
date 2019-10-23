package com.github.springbootredis.schedule;

import com.alibaba.fastjson.JSON;
import com.github.springbootredis.pojo.Person;
import com.github.springbootredis.repository.PersonKeyValueRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.github.springbootredis.common.PersonFactory.getPerson;

/**
 * Created in 16:04 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@EnableScheduling
public class SchedulePersonSaveTask {

    @Resource
    private PersonKeyValueRepository repository;

    /**
     * 每秒使用 Repository 方式向数据库中插入一条消息
     */
    @Scheduled(fixedRate = 1000)
    public void task() {
        Person person = getPerson();
        System.out.println(JSON.toJSONString(person));
        repository.save(person);
    }

}