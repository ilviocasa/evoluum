package com.github.ilviocasa.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Caching Config is responsible to enable the Caching module, which 
 * provides a way to improve the performance of the system.
 * 
 * @author silvio
 *
 */
@Configuration
@EnableCaching
public class CachingConfig {
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("findCidadeByName");
    }
}
