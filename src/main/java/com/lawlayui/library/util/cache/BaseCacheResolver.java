package com.lawlayui.library.util.cache;

import java.util.Collection;
import java.util.Collections;

import org.springframework.aop.support.AopUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import com.lawlayui.library.service.Service;


public class BaseCacheResolver extends SimpleCacheResolver {
    public BaseCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        Object target = context.getTarget();
    
        Class<?> targetClass = AopUtils.getTargetClass(target);

        if (Service.class.isAssignableFrom(targetClass)) {
            String dynamicCacheName = ((Service) target).getCacheNames();
            return Collections.singletonList(dynamicCacheName);
        }

        return super.getCacheNames(context);
    } 
}
