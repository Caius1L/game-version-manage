package com.handarui.game.config;

import com.handarui.game.biz.config.MysqlConfig;
import com.handarui.game.biz.config.RedisDisconfConfig;
import com.handarui.game.dao.util.UpdateInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SpringConfig {

    @Bean
    @DependsOn("disconfMgrBean2")
    public HikariDataSource hikariDataSource(MysqlConfig dbConfig) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbConfig.getDataSourceUrl());
        config.setDriverClassName(dbConfig.getDataSourceDriverClassName());
        config.setUsername(dbConfig.getDataSourceUsername());
        config.setPassword(dbConfig.getDataSourcePassword());
        config.setMaximumPoolSize(dbConfig.getMaxPoolSize());
        config.setConnectionTimeout(600000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(28770000);
        return new HikariDataSource(config);
    }

    @Bean
    @DependsOn("disconfMgrBean2")
    public JedisConnectionFactory connectionFactory(RedisDisconfConfig redisConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisConfig.getRedisHost());
        factory.setPassword(redisConfig.getRedisPassword());
        factory.setPort(redisConfig.getRedisPort());
        factory.setUsePool(true);
        factory.setTimeout(redisConfig.getRedisTimeOut());
        factory.getPoolConfig().setMaxIdle(-1);
        factory.getPoolConfig().setMaxTotal(-1);
        factory.getPoolConfig().setMaxWaitMillis(60000);
        return factory;
    }

    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer valueSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    /**
     * 给mybatis添加插件
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(HikariDataSource dataSource) {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        Interceptor[] plugins = {new UpdateInterceptor()};
        sfb.setPlugins(plugins);
        return sfb;
    }
}
