package com.lawlayui.library.util.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lawlayui.library.api.dto.request.BaseRequestDTO;
import com.lawlayui.library.api.dto.response.BaseResponseDTO;
import com.lawlayui.library.entity.BaseEntity;

public interface BaseMapper<ID, E extends BaseEntity<ID>, Req extends BaseRequestDTO, UReq extends BaseRequestDTO, Res extends BaseResponseDTO<ID>>{
    E requestToEntity(Req request);
    Res entityToResponse(E entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UReq updateRequest, @MappingTarget E entity);

    List<Res> entityToResponses(List<E> entities);
}
