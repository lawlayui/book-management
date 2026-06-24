package com.lawlayui.library.util.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;
import com.lawlayui.library.entity.BaseEntity;

public interface GenericMapper<ID, E extends BaseEntity<ID>, Req extends BaseRequest, UReq extends BaseRequest, Res extends BaseResponse<ID>>{
    E requestToEntity(Req request);

    Res entityToResponse(E entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UReq updateRequest, @MappingTarget E entity);

    List<Res> entityToResponses(List<E> entities);
}
