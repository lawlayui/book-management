package com.lawlayui.library.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.entity.Book;
import com.lawlayui.library.repository.BookRepository;
import com.lawlayui.library.service.BookService;

@SpringBootTest
@EnableCaching
@SuppressWarnings("null")
public class BookCacheTest {
    @Autowired
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        if (cacheManager.getCache("books") != null) {
            cacheManager.getCache("books").clear();
        }
    }
    
    @AfterEach
    void closeUp() {
        if (cacheManager.getCache("books") != null) {
            cacheManager.getCache("books").clear();
        }
    }
    
    @Test
    void testGetBookBYId_UseCache() throws Exception {
        Book book = Book.builder()
        .id(1L)
        .title("This is title")
        .author("This is author")
        .description("This is description")
        .price(BigDecimal.valueOf(12.12))
        .stock(12L)
        .build();

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDTO b1 = bookService.findById(1L);

        BookResponseDTO b2 = bookService.findById(1L);

        Mockito.verify(bookRepository, Mockito.times(1)).findById(1L);
        assertEquals("This is title", b1.getTitle());
        assertEquals("This is title", b2.getTitle());
    }
    
    @Test
    void testUpdateBookCache_deleteCache() throws Exception {
        Book savedBook = Book.builder()
        .id(1L)
        .title("This is title")
        .author("This is author")
        .description("This is description")
        .price(BigDecimal.valueOf(12.12))
        .stock(12L)
        .build();
        Cache cache = cacheManager.getCache("books");
        if (cache != null) {
            cache.put(savedBook.getId(), savedBook);
        }

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(savedBook));

        Book updatedBook = Book.builder()
        .id(1L)
        .title("This is title")
        .author("This is author")
        .description("This is description")
        .price(BigDecimal.valueOf(12.12))
        .stock(11L)
        .build();
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(updatedBook);

        BookUpdateRequestDTO bookRequest = BookUpdateRequestDTO.builder()
        .title("This is title")
        .author("This is author")
        .description("This is description")
        .price(BigDecimal.valueOf(12.12))
        .stock(11L)
        .build();
        BookResponseDTO bookRESP = bookService.update(1L, bookRequest);
        
        assertNotNull(bookRESP);
        assertEquals(11L, bookRESP.getStock());
        assertNull(cache.get(1L));
    }

    @Test
    void testDeleteCache_deleteCache() {
        Book savedBook = Book.builder()
        .id(1L)
        .title("This is title")
        .author("This is author")
        .description("This is description")
        .price(BigDecimal.valueOf(12.12))
        .stock(12L)
        .build();
        Cache cache = cacheManager.getCache("books");
        if (cache != null) {
            cache.put(savedBook.getId(), savedBook);
        }

        bookService.delete(1L);
        Mockito.verify(bookRepository, times(1)).deleteById(1L);

        assertNull(cache.get(1L));
    }
}
