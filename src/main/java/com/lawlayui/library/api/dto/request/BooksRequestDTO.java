package com.lawlayui.library.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BooksRequestDTO extends BaseRequestDTO{

    public BooksRequestDTO(String title,
            String description, String author, BigDecimal price, int stock, int year) {
        this.year = year;
        this.title = title;
        this.description = description;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
    
    @Max(value = 255, message = "The maximum title limit is 255 characters")
    private String title;
    
    @Max(value = 255, message = "The maximum description limit is 255 characters")
    private String description;

    @Max(value = 255, message = "The maximum author limit is 255 characters")
    private String author;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be zero")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be zero")
    private int stock;

    private int year;
}
