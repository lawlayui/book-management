package com.lawlayui.library.service;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;
import com.lawlayui.library.entity.BaseEntity;

public interface CanSave <E extends BaseEntity<ID>, RES extends BaseResponse<ID>, REQ extends BaseRequest, ID> { 
    default RES save(REQ request) throws Exception {return null;}; 
}
