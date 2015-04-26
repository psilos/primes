package com.kpapalias.exercise.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;

@ComponentScan
@EnableAutoConfiguration
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CacheManager cacheManager() throws IOException {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() throws IOException {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setCacheManagerName("primes");
        cmfb.setShared(true);
        cmfb.afterPropertiesSet();
        return cmfb;
    }

}
