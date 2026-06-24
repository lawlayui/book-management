package com.lawlayui.library.api.controller;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;

public interface CrudController<RES extends BaseResponse<ID>, REQ extends BaseRequest, UREQ extends BaseRequest, ID> extends CreatableController<RES, REQ, ID>, ReadableController<RES, ID>, DeletableController<ID>, PatchableController<RES, UREQ, ID>{
    
}
