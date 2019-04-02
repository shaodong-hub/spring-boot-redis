package com.github.springbootredis.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created in 16:15 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Getter
@Setter
@EqualsAndHashCode
public class Mapping {

    private String sha;

    private String[] args;

}
