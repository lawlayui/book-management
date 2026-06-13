package com.lawlayui.library.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper extends GenericMapper<Long, Book, BookRequestDTO, BookUpdateRequestDTO, BookResponseDTO>{
    
}
