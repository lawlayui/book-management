package learn.example.database_migration.repository;


import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import learn.example.database_migration.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Long>{
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByTitle(String title, Pageable pageable);
}
