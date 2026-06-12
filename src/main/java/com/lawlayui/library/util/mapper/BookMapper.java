package com.lawlayui.library.util.mapper;

import org.mapstruct.Mapper;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<Long, Book, BookRequestDTO, BookUpdateRequestDTO, BookResponseDTO>{
    
}
