package learn.example.database_migration.api.controller;

import org.springframework.web.bind.annotation.RestController;

import learn.example.database_migration.api.dto.request.BooksRequestDTO;
import learn.example.database_migration.api.dto.response.BooksResponseDTO;
import learn.example.database_migration.entity.Book;
import learn.example.database_migration.service.BooksService;

@RestController
public class BooksController extends BaseController<Book, Long, BooksResponseDTO, BooksRequestDTO> {
    public BooksController(BooksService service) {
        this.service = service;
    }
}
