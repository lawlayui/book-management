package com.lawlayui.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.repository.BooksRepository;
import com.lawlayui.library.util.mapper.BookMapper;

@Service
public class BookService extends BaseService<Book, Long, BookResponseDTO, BookRequestDTO, BookUpdateRequestDTO>{
    public BookService(BooksRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BookResponseDTO> mapToResponse(List<Book> books){
        List<BookResponseDTO> booksResponse = books.stream()
            .map((book) -> new BookResponseDTO(book.getId(), book.getCreatedAt(), book.getUpdateAt(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getPrice(), book.getStock(), book.getPublication_year()))
            .collect(Collectors.toList());

        return booksResponse;
    }

    @Override
    public BookResponseDTO update(Long id, BookUpdateRequestDTO request) throws ResourceNotFound {
        Book book = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Resource with id: " + id + " not found"));

        mapper.updateEntityFromDto(request, book);;
        return mapper.entityToResponse(repository.save(book));
    }
}
