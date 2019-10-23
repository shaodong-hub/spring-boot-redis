package com.github.springbootredis.repository;

import com.github.springbootredis.pojo.Person;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

/**
 * Created in 16:03 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


public interface PersonKeyValueRepository extends KeyValueRepository<Person, String> {

}
