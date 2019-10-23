package com.github.springbootredis.common;

import com.github.springbootredis.pojo.Person;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * 产生 Person对象 的工厂
 *
 * <p>
 * 创建时间为 18:15 2019-04-12
 * 项目名称 spring-boot-redis
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public class PersonFactory {

    @NotNull
    public static Person getPerson() {
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
