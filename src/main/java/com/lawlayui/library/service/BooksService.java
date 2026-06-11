package com.lawlayui.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.BooksRequestDTO;
import com.lawlayui.library.api.dto.request.BooksUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BooksResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.repository.BooksRepository;
import com.lawlayui.library.util.interface_mapper.BookMapper;

@Service
public class BooksService extends BaseService<Book, Long, BooksResponseDTO, BooksRequestDTO, BooksUpdateRequestDTO>{
    private BookMapper bookMapper;
    public BooksService(BooksRepository repository, BookMapper bookMapper) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }
    public Book mapToEntity(BooksRequestDTO request) {
        Book book = new Book(null, request.getTitle(), request.getDescription(), request.getAuthor(), request.getYear(), request.getPrice(), request.getStock());
        return book;
    }

    public BooksResponseDTO mapToResponse(Book book) {
        BooksResponseDTO bookResponse = BooksResponseDTO.builder() 
        .id(book.getId())
        .title(book.getTitle())
        .author(book.getAuthor())
        .description(book.getDescription())
        .createdAt(book.getCreatedAt())
        .updateAt(book.getUpdateAt())
        .build();
        return bookResponse;
    }

    public List<BooksResponseDTO> mapToResponse(List<Book> books){
        List<BooksResponseDTO> booksResponse = books.stream()
            .map((book) -> new BooksResponseDTO(book.getId(), book.getCreatedAt(), book.getUpdateAt(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getPrice(), book.getStock(), book.getPublication_year()))
            .collect(Collectors.toList());

        return booksResponse;
    }

    @Override
    public BooksResponseDTO update(Long id, BooksUpdateRequestDTO request) throws ResourceNotFound {
        Book book = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Resource with id: " + id + " not found"));

        bookMapper.udpateBookFromDto(request, book);;
        repository.save(book);
        return mapToResponse(book);
    }
}
