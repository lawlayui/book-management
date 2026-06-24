package com.lawlayui.library.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequest extends BaseRequest{

    @Builder
    public BookRequest(String title,
            String description, String author, BigDecimal price, Long stock, int pubication_year) {
        this.pubication_year = pubication_year;
        this.title = title;
        this.description = description;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
    
    @NotBlank
    @Size(max = 255, message = "The maximum title limit is 255 characters")
    private String title;
    
    @NotBlank
    @Size(max = 255, message = "The maximum description limit is 255 characters")
    private String description;

    @NotBlank
    @Size(max = 255, message = "The maximum author limit is 255 characters")
    private String author;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", message = "price cannot be less than zero")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be zero")
    private Long stock;

    @NotNull(message = "pubication_year cannot be null")
    private int pubication_year;
}
