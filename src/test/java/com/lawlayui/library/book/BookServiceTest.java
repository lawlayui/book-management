package com.lawlayui.library.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.repository.BookRepository;
import com.lawlayui.library.service.BookService;
import com.lawlayui.library.util.mapper.BookMapper;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
    private BookService bookService;

    @BeforeEach
    void setUp() {
        this.bookService = new BookService(bookRepository, bookMapper);
    }

    @Test
    void findById_ResourceNotFoundException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFound.class, () -> bookService.findById(1L));
    }

    @Test
    void saveWithMapper_Success() {
        Book savedBook = Book.builder()
            .id(1L)
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .year(2010)
            .build();
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        BookRequestDTO bookRequestDTO = BookRequestDTO.builder()
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .pubication_year(2010)
            .build();
        BookResponseDTO resp = bookService.save(bookRequestDTO);

        assertThat(resp).isNotNull();
        assertThat(resp.getTitle()).isEqualTo("TITLE BOOK");
        assertThat(resp.getId()).isEqualTo(1L);
    }

    @Test
    void updateBook_Success() throws Exception{
        Book savedBook = Book.builder()
            .id(1L)
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .year(2010)
            .build();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(savedBook));

        Book updatedBook = Book.builder()
            .id(1L)
            .title("Title changed")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(13.12))
            .stock(11L)
            .year(2010)
            .build();
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        BookUpdateRequestDTO updateRequestBook = BookUpdateRequestDTO.builder()
            .title("Title changed")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(13.12))
            .stock(11L)
            .publication_year(2010)
            .build();
        BookResponseDTO resp = bookService.update(1L, updateRequestBook);

        assertThat(resp).isNotNull();
        assertThat(resp.getTitle()).isEqualTo("Title changed");
        assertThat(resp.getPrice()).isEqualTo(BigDecimal.valueOf(13.12));
        assertThat(resp.getStock()).isEqualTo(11L);
    }
    
    
}
