package com.lawlayui.library.api.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooksResponseDTO extends BaseResponseDTO<Long>{
    @Builder
    public BooksResponseDTO(Long id, LocalDateTime createdAt, LocalDateTime updateAt, String title, String description, String author, BigDecimal price, int stock, int year) {
        super(id, Instant.now(), createdAt, updateAt);
        this.title = title;
        this.year = year;
        this.description = description;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
    private String title;
    private String description;
    private String author;
    private BigDecimal price;
    private int stock;
    private int year;
}
