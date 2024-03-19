package ru.berdnikov.edu_learn.caching;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.management.timer.Timer;
import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {
    private final Logger logger;

    @Autowired
    public CachingConfig(Logger logger) {
        this.logger = logger;
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("games")
        ));
        return cacheManager;
    }

    @Scheduled(fixedRate = Timer.ONE_HOUR)
    @Caching(evict = {
            @CacheEvict(cacheNames="games", allEntries=true),
    })
    public void clearAllCaches() {
        logger.info("Clearing all caches entries");
    }
}
