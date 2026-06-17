package com.lawlayui.library.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import com.lawlayui.library.service.BaseService;

public class BaseCacheResolver extends SimpleCacheResolver {
    public BaseCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        Object target = context.getTarget();
    
        if (target instanceof BaseService) {
            String dynamicCacheName = ((BaseService<?, ?, ?, ?, ?>) target).getCacheNames();
            return Collections.singletonList(dynamicCacheName);
        }

        return super.getCacheNames(context);
    } 
}
