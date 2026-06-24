package com.lawlayui.library.service;

import org.springframework.cache.annotation.CacheEvict;

import com.lawlayui.library.entity.BaseEntity;


public interface CanDelete<E extends BaseEntity<ID>, ID> {
    @CacheEvict(value = "baseCacheResolver", key = "#id")
    default void delete(ID id) throws Exception {return;};
}
