package com.github.springbootredis.script;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created in 16:01 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Component
public class ScriptHyperLogLog {

    @Resource
    private StringRedisConnection connection;

    @Bean("HyperLogLog")
    @SneakyThrows(IOException.class)
    public RedisScript script() {
        ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("scripts/HyperLogLog.lua"));
        RedisScript script = RedisScript.of(scriptSource.getScriptAsString(), Boolean.class);
        connection.scriptLoad(script.getScriptAsString());
        return RedisScript.of(scriptSource.getScriptAsString(), Boolean.class);
    }

}
