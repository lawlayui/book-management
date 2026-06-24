package com.lawlayui.library.service;

import org.springframework.cache.annotation.CacheEvict;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;
import com.lawlayui.library.entity.BaseEntity;

public interface CanUpdate<E extends BaseEntity<ID>, RES extends BaseResponse<ID>, UREQ extends BaseRequest, ID>{
    @CacheEvict(value = "baseCacheResolver", key = "#id")
    default RES update(UREQ request, ID id) throws Exception {return null;}
}
