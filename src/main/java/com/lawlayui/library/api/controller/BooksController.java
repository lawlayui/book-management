package com.lawlayui.library.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.dto.request.BooksRequestDTO;
import com.lawlayui.library.api.dto.response.BooksResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.service.BooksService;

@RestController
public class BooksController extends BaseController<Book, Long, BooksResponseDTO, BooksRequestDTO> {
    public BooksController(BooksService service) {
        this.service = service;
    }
}
