package com.lawlayui.library.service;


import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.repository.BookRepository;
import com.lawlayui.library.util.mapper.BookMapper;

import jakarta.transaction.Transactional;

@Service
public class BookService extends BaseService<Book, Long, BookResponseDTO, BookRequestDTO, BookUpdateRequestDTO>{
    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public String getCacheNames() {
        return "books";
    }

    @Transactional
    @Override
    public BookResponseDTO update(Long id, BookUpdateRequestDTO request) throws ResourceNotFound {
        Book book = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Resource with id: " + id + " not found"));

        mapper.updateEntityFromDto(request, book);;
        return mapper.entityToResponse(repository.save(book));
    }
}
