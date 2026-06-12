package com.lawlayui.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController<Book, Long, BookResponseDTO, BookRequestDTO, BookUpdateRequestDTO> {
    public BookController(BookService service) {
        this.service = service;
    }
}
