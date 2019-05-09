package com.github.springbootredis.dao;

import com.github.springbootredis.pojo.Mapping;
import com.github.springbootredis.pojo.Person;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in 16:15 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Repository
public class PersonDao {

    @Resource(name = "RedisTemplateSerializable")
    private RedisTemplate<String, Person> template;

    public List<Long> getResult(Set<Mapping> set) {
        List<Object> results = template.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection redisConnection = (StringRedisConnection) connection;
            set.forEach(one -> redisConnection.evalSha(one.getSha(), ReturnType.INTEGER, 1, one.getArgs()));
            return redisConnection;
        });
        return results.stream().map(Long.class::cast).collect(Collectors.toList());
    }

    public void delete(String prefix) {
        Set<String> keys = template.keys(prefix + "*");
        Long nums = template.delete(keys);
    }

    public void clearAll(){
        template.delete(template.keys("*"));
    }


}