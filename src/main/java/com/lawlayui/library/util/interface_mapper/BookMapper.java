package com.lawlayui.library.util.interface_mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lawlayui.library.api.dto.request.BooksUpdateRequestDTO;
import com.lawlayui.library.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void udpateBookFromDto(BooksUpdateRequestDTO dto, @MappingTarget Book entity);
}
