package com.lawlayui.library.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawlayui.library.util.cache.BaseCacheResolver;


@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        ObjectMapper objectMapper = new ObjectMapper();       
        objectMapper.registerModule(new JavaTimeModule());

        RedisSerializer<Object> jsonSerializer = RedisSerializer.json();

        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer));
    }
    
    @Bean
    public CacheResolver baseCacheResolver(CacheManager cacheManager) {
        return new BaseCacheResolver(cacheManager);
    }
}
