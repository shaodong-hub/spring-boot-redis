package com.github.springbootredis.schedule;

import com.alibaba.fastjson.JSON;
import com.github.springbootredis.pojo.Person;
import com.github.springbootredis.repository.PersonPagingAndSortingRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

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
    private PersonPagingAndSortingRepository repository;

    @Scheduled(fixedRate = 1000)
    public void task() {
        Person person = getPerson();
        System.out.println(JSON.toJSONString(person));
        repository.save(person);
    }

    private static Person getPerson() {
        Random random = new Random();
        Person person = new Person();
        int data = random.nextInt(200);
        person.setId(data + "");
        person.setAge(data);
        person.setName("name:" + data);
        person.setPass("pass:" + data);
        person.setExpiration(999L);
        return person;
    }

}