package com.lawlayui.library.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lawlayui.library.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Long>{
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByTitle(String title, Pageable pageable);
}
