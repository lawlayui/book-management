package com.lawlayui.library.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.lawlayui.library.api.dto.request.BookRequest;
import com.lawlayui.library.api.dto.request.BookUpdateRequest;
import com.lawlayui.library.api.dto.response.BookResponse;
import com.lawlayui.library.entity.Book;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper extends GenericMapper<Long, Book, BookRequest, BookUpdateRequest, BookResponse>{
}
