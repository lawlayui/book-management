package com.lawlayui.library.book;

import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawlayui.library.api.controller.BookController;
import com.lawlayui.library.api.dto.request.BookRequestDTO;
import com.lawlayui.library.api.dto.request.BookUpdateRequestDTO;
import com.lawlayui.library.api.dto.response.BookResponseDTO;
import com.lawlayui.library.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void testGetById_Success() throws Exception{
        BookResponseDTO book = BookResponseDTO.builder()
            .id(1L)
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .publication_year(2010)
            .build();
        Mockito.when(bookService.findById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("TITLE BOOK"));
    }

    @Test
    void testGetAll_Success() throws Exception{
        List<BookResponseDTO> books = List.of(
            BookResponseDTO.builder()
            .id(1L)
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .publication_year(2010)
            .build(),
            BookResponseDTO.builder()
            .id(2L)
            .title("BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(13.12))
            .stock(1L)
            .publication_year(2010)
            .build()
        );
        Mockito.when(bookService.findAll(PageRequest.of(0, 2))).thenReturn(books);
    
        mockMvc.perform(get("/api/books?page=0&size=2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[1].title").value("BOOK"));
    }
    
    @Test
    void testSave_Success() throws Exception{
        BookResponseDTO book = BookResponseDTO.builder()
            .id(1L)
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .publication_year(2010)
            .build();
        Mockito.when(bookService.save(Mockito.any(BookRequestDTO.class))).thenReturn(book);


        BookRequestDTO requestBook = BookRequestDTO.builder()
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .pubication_year(2010)
            .build();
        mockMvc.perform(post("/api/books")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestBook)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("TITLE BOOK"));
    }
    
    @Test
    void testUpdate_Success() throws Exception{
        BookResponseDTO book = BookResponseDTO.builder()
            .id(1L)
            .title("BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(11L)
            .publication_year(2010)
            .build();
        Mockito.when(bookService.update(eq(1L), Mockito.any(BookUpdateRequestDTO.class))).thenReturn(book);

        BookUpdateRequestDTO requestBook = BookUpdateRequestDTO.builder()
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .publication_year(2010)
            .build();
        mockMvc.perform(patch("/api/books/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestBook)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("BOOK"))
        .andExpect(jsonPath("$.price").value(12));
    }
}
