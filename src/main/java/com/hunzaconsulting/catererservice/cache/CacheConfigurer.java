package com.hunzaconsulting.catererservice.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
@EnableCaching
public class CacheConfigurer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheNames(asList("catererById", "catererByName", "catererByCity"));
    }
}
