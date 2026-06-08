package learn.example.database_migration.entity;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseEntity<Long>{
    @Builder
    public Book(Long id, String title, String description, String author, int year, BigDecimal price, int stock) {
        super(id);
        this.title = title;
        this.description = description;
        this.author = author;
        this.publication_year = year;
        this.price = price;
        this.stock = stock;
    }
    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 255)
    private String description;

    private int publication_year;

    private BigDecimal price;

    private int stock;
}
