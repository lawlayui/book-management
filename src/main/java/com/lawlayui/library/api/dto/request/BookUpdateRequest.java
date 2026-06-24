package com.lawlayui.library.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookUpdateRequest extends BaseRequest{
    
    @Size(max = 255, message = "The maximum title limit is 255 characters")
    private String title;

    @Size(max = 255, message = "The maximum author limit is 255 characters")
    private String author;

    @Size(max = 2555, message = "The maximum description limit is 255 characters")
    private String description;

    @DecimalMin(value = "0.0", message = "Price cannot be less than zero")
    private BigDecimal price;

    @Min(value = 0, message = "Stock minimal is 0")
    private Long stock;
    private int publication_year;
}
