//package com.github.springbootredis.config;
//
//import io.lettuce.core.resource.ClientResources;
//import lombok.SneakyThrows;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//
///**
// * Created in 15:59 2019-04-01
// * Project name spring-boot-redis
// *
// * @author shao
// * @version 0.0.1
// * @since 0.0.1
// */
//
//
//@Configuration
//public class ConfigConnectionFactory {
//
//    @Resource
//    private RedisProperties properties;
//
//    @Resource
//    private ClientResources clientResources;
//
//    @Resource
//    private List<LettuceClientConfigurationBuilderCustomizer> builderCustomizers;
//
//    @Primary
//    @Bean(name = "Factory0")
//    public LettuceConnectionFactory redisConnectionFactory0() {
//        return getLettuceConnectionFactory(0);
//    }
//
//    @Bean(name = "Factory2")
//    public LettuceConnectionFactory redisConnectionFactory2() {
//        return getLettuceConnectionFactory(2);
//    }
//
//    @Bean(name = "Factory6")
//    public LettuceConnectionFactory redisConnectionFactory6() {
//        return getLettuceConnectionFactory(6);
//    }
//
//    @Bean(name = "Factory8")
//    public LettuceConnectionFactory redisConnectionFactory8() {
//        return getLettuceConnectionFactory(8);
//    }
//
//    @NotNull
//    private LettuceConnectionFactory getLettuceConnectionFactory(int i) {
//        LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(clientResources, this.properties.getLettuce().getPool());
//        return createLettuceConnectionFactory(clientConfig, i);
//    }
//
//    private LettuceConnectionFactory createLettuceConnectionFactory(LettuceClientConfiguration clientConfiguration, int database) {
//        return new LettuceConnectionFactory(getStandaloneConfig(database), clientConfiguration);
//    }
//
//    private RedisStandaloneConfiguration getStandaloneConfig(int database) {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        if (StringUtils.hasText(this.properties.getUrl())) {
//            ConnectionInfo connectionInfo = parseUrl(this.properties.getUrl());
//            config.setHostName(connectionInfo.getHostName());
//            config.setPort(connectionInfo.getPort());
//            config.setPassword(RedisPassword.of(connectionInfo.getPassword()));
//        } else {
//            config.setHostName(this.properties.getHost());
//            config.setPort(this.properties.getPort());
//            config.setPassword(RedisPassword.of(this.properties.getPassword()));
//        }
//        config.setDatabase(database);
//        return config;
//    }
//
//    @SneakyThrows(URISyntaxException.class)
//    private ConnectionInfo parseUrl(String url) {
//        URI uri = new URI(url);
//        boolean useSsl = (url.startsWith("rediss://"));
//        String password = null;
//        if (uri.getUserInfo() != null) {
//            password = uri.getUserInfo();
//            int index = password.indexOf(':');
//            if (index >= 0) {
//                password = password.substring(index + 1);
//            }
//        }
//        return new ConnectionInfo(uri, useSsl, password);
//    }
//
//    private LettuceClientConfiguration getLettuceClientConfiguration(ClientResources clientResources, RedisProperties.Pool pool) {
//        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = createBuilder(pool);
//        applyProperties(builder);
//        if (StringUtils.hasText(this.properties.getUrl())) {
//            customizeConfigurationFromUrl(builder);
//        }
//        builder.clientResources(clientResources);
//        customize(builder);
//        return builder.build();
//    }
//
//    private void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder builder) {
//        for (LettuceClientConfigurationBuilderCustomizer customizer : this.builderCustomizers) {
//            customizer.customize(builder);
//        }
//    }
//
//
//    private void customizeConfigurationFromUrl(LettuceClientConfiguration.LettuceClientConfigurationBuilder builder) {
//        ConnectionInfo connectionInfo = parseUrl(this.properties.getUrl());
//        if (connectionInfo.isUseSsl()) {
//            builder.useSsl();
//        }
//    }
//
//    private LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool pool) {
//        return (pool == null) ? LettuceClientConfiguration.builder() : new PoolBuilderFactory().createBuilder(pool);
//    }
//
//
//    private static class PoolBuilderFactory {
//        LettuceClientConfiguration.LettuceClientConfigurationBuilder createBuilder(RedisProperties.Pool properties) {
//            return LettucePoolingClientConfiguration.builder().poolConfig(getPoolConfig(properties));
//        }
//
//        private GenericObjectPoolConfig<?> getPoolConfig(RedisProperties.Pool properties) {
//            GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
//            config.setMaxTotal(properties.getMaxActive());
//            config.setMaxIdle(properties.getMaxIdle());
//            config.setMinIdle(properties.getMinIdle());
//            if (properties.getMaxWait() != null) {
//                config.setMaxWaitMillis(properties.getMaxWait().toMillis());
//            }
//            return config;
//        }
//    }
//
//    private void applyProperties(LettuceClientConfiguration.LettuceClientConfigurationBuilder builder) {
//        if (this.properties.isSsl()) {
//            builder.useSsl();
//        }
//
//        if (this.properties.getTimeout() != null) {
//            builder.commandTimeout(this.properties.getTimeout());
//        }
//
//        if (this.properties.getLettuce() != null) {
//            RedisProperties.Lettuce lettuce = this.properties.getLettuce();
//            if (lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
//                builder.shutdownTimeout(this.properties.getLettuce().getShutdownTimeout());
//            }
//        }
//    }
//
//    private class ConnectionInfo {
//        private final URI uri;
//        private final boolean useSsl;
//        private final String password;
//
//        ConnectionInfo(URI uri, boolean useSsl, String password) {
//            this.uri = uri;
//            this.useSsl = useSsl;
//            this.password = password;
//        }
//
//        boolean isUseSsl() {
//            return this.useSsl;
//        }
//
//        String getHostName() {
//            return this.uri.getHost();
//        }
//
//        int getPort() {
//            return this.uri.getPort();
//        }
//
//        String getPassword() {
//            return this.password;
//        }
//    }
//}
