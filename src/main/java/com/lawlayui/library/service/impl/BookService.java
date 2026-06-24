package com.lawlayui.library.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.lawlayui.library.api.dto.request.BookRequest;
import com.lawlayui.library.api.dto.request.BookUpdateRequest;
import com.lawlayui.library.api.dto.response.BookResponse;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.exception.ResourceNotFoundException;
import com.lawlayui.library.repository.BookRepository;
import com.lawlayui.library.service.CanCrud;
import com.lawlayui.library.service.Service;
import com.lawlayui.library.util.mapper.BookMapper;

import jakarta.transaction.Transactional;

public class BookService implements CanCrud<Book, BookResponse, BookRequest, BookUpdateRequest, Long>, Service{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public String getCacheNames() {
        return "book";
    }

    @Override
    @Transactional
    public List<BookResponse> findAll(int size, int page) throws Exception {
        return bookMapper.entityToResponses(bookRepository.findAll(PageRequest.of(page, size)).getContent());
    }

    @Override
    @Transactional
    public BookResponse findById(Long id) throws Exception {
        return bookMapper.entityToResponse(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookResponse save(BookRequest request) throws Exception {
        return bookMapper.entityToResponse(bookRepository.save(bookMapper.requestToEntity(request)));
    }

    @Override
    @Transactional
    public BookResponse update(BookUpdateRequest request, Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookMapper.updateEntityFromDto(request, book);
        return bookMapper.entityToResponse(bookRepository.save(book));
    }
    
}
