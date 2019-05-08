package com.handarui.game.config;

import com.handarui.auth.adapters.form.FormLoginAdapter;
import com.handarui.auth.config.AuthRedisConfig;
import com.handarui.auth.model.AuthConfig;
import com.handarui.auth.model.RealmsHolder;
import com.handarui.auth.model.ShiroFilterMapHolder;
import com.handarui.auth.model.UserContextRepository;
import com.handarui.auth.service.LoginAdapterCollection;
import com.handarui.auth.service.impl.SimpleLoginAdapterCollection;
import com.handarui.game.biz.config.RedisDisconfConfig;
import com.handarui.game.biz.shiro.filter.UrlPermissionFilter;
import com.handarui.game.biz.shiro.realm.ClearAbleAccountUserRealm;
import org.apache.shiro.realm.Realm;
import org.crazycake.shiro.RedisManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.servlet.Filter;
import java.util.*;

@Configuration
@EnableRedisRepositories(basePackageClasses = UserContextRepository.class,keyspaceConfiguration = AuthRedisConfig.MyKeyspaceConfiguration.class)
public class UserLoginConfig {

    @Bean
    public LoginAdapterCollection LoginAdapterCollection() {
        SimpleLoginAdapterCollection adapterCollection = new SimpleLoginAdapterCollection();
        FormLoginAdapter formLoginAdapter = new FormLoginAdapter();
        adapterCollection.add(formLoginAdapter);
        return adapterCollection;
    }

    @Bean
    public RealmsHolder realmsHolder(ClearAbleAccountUserRealm clearAbleAccountUserRealm){
        List<Realm> realms = new ArrayList<>();
        realms.add(clearAbleAccountUserRealm);
        return new RealmsHolder(realms);
    }

    @Bean
    public UrlPermissionFilter urlPermissionFilter(){
        return new UrlPermissionFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(UrlPermissionFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public ShiroFilterMapHolder shiroFilterMapHolder() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("urlPermissionFilter",urlPermissionFilter());
        return new ShiroFilterMapHolder(filters);
    }

    @Bean("filterChainDefinitionMap")
    public Map<String, String> filterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/service-worker.js", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/auth/*", "anon");
        filterChainDefinitionMap.put("/sts/*", "anon");
        filterChainDefinitionMap.put("/**", "urlPermissionFilter");
        return filterChainDefinitionMap;
    }

    @Bean
    @DependsOn("disconfMgrBean2")
    public AuthConfig authConfig(RedisDisconfConfig redisDisconfConfig) {
        AuthConfig authConfig = new AuthConfig();
        authConfig.setTokenStorageNamespace(redisDisconfConfig.getTokenStorageNamespace());
        authConfig.setSessionStorageNamespace(redisDisconfConfig.getSessionStorageNamespace());
        authConfig.setTokenExpiration(redisDisconfConfig.getTokenExpiration());
        authConfig.setSingleSessionMode(false);
        return authConfig;
    }

    @Bean
    @DependsOn("disconfMgrBean2")
    public RedisManager redisManager(RedisDisconfConfig redisDisconfConfig) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisDisconfConfig.getRedisHost());
        redisManager.setPort(redisDisconfConfig.getRedisPort());
        redisManager.setPassword(redisDisconfConfig.getRedisPassword());
        redisManager.setTimeout(redisDisconfConfig.getRedisTimeOut());
        return redisManager;
    }

}
