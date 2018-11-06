package com.xiaobai.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class CacheLocal {

    @Bean(name = "userMap")
    public ConcurrentMap<String,Object> userMap() {
        return new ConcurrentHashMap<String,Object>();
    }

}
