package com.lawlayui.library.book;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import com.lawlayui.library.entity.Book;
import com.lawlayui.library.repository.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findById() {
        Book book = Book.builder()
            .title("TITLE BOOK")
            .author("Lawlayui")
            .description("This is a book")
            .price(BigDecimal.valueOf(12.12))
            .stock(12L)
            .year(2010)
            .build();
        entityManager.persistAndFlush(book);

        Optional<Book> optionalBook = bookRepository.findById(1L);

        assertThat(optionalBook.get())
            .isNotNull();
        assertThat(optionalBook.get().getTitle())
            .isEqualTo("TITLE BOOK");
        assertThat(optionalBook.get().getId())
            .isEqualTo(1l);
    }
}
