package com.lawlayui.library.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.lawlayui.library.api.dto.response.BaseResponse;
import com.lawlayui.library.entity.BaseEntity;

public interface CanFind<E extends BaseEntity<ID>, RES extends BaseResponse<ID>, ID> {
    @Cacheable(value = "baseCacheResolver", key = "#id")
    default RES findById(ID id) throws Exception {return null;}
    default List<RES> findAll(int size, int page) throws Exception {return null;}
}
