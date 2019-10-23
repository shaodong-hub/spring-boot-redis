package com.github.springbootredis.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * Created in 15:59 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Getter
@Setter
@ToString
@NoArgsConstructor
@RedisHash("people")
public class Person implements Serializable {

    private static final long serialVersionUID = -5131273498855124834L;

    @Id
    @Indexed
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String pass;

    @Indexed
    private Integer age;

    @TimeToLive
    private Long expiration;

}