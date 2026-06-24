package com.lawlayui.library.service;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;
import com.lawlayui.library.entity.BaseEntity;

public interface CanCrud<E extends BaseEntity<ID>, RES extends BaseResponse<ID>, REQ extends BaseRequest, UREQ extends BaseRequest, ID> extends CanFind<E, RES, ID>, CanDelete<E, ID>, CanSave<E, RES, REQ, ID>, CanUpdate<E, RES, UREQ, ID> {
}
