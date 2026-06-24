package com.lawlayui.library.api.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.controller.CrudController;
import com.lawlayui.library.api.dto.request.BookRequest;
import com.lawlayui.library.api.dto.request.BookUpdateRequest;
import com.lawlayui.library.api.dto.response.BookResponse;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.service.CanCrud;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController implements CrudController<BookResponse, BookRequest, BookUpdateRequest, Long>{

    private final CanCrud<Book, BookResponse, BookRequest, BookUpdateRequest, Long> bookService;

    public BookController(CanCrud<Book, BookResponse, BookRequest, BookUpdateRequest, Long> bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<BookResponse> create(BookRequest request) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(request));
    }

    @Override
    public ResponseEntity<List<BookResponse>> getAll(int size, int page) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(size, page));
    }

    @Override
    public ResponseEntity<BookResponse> getById(Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<BookResponse> update(@Valid BookUpdateRequest updateRequest, Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(updateRequest, id));
    }
    
}
