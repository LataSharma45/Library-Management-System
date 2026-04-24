package project.LibraryManage.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import project.LibraryManage.dto.BookDto;
import project.LibraryManage.entity.BookEntity;

import java.util.Optional;

@Service
public interface BookService {

    BookDto saveBook(BookDto book);


    Page<BookEntity> findAll(int page, int size, String sortBy);

    Optional<BookEntity> findOne(Long id);

    boolean exist(Long id);

    BookDto updateBook(Long id , BookDto book);

    BookEntity partialUpdate(Long id, BookEntity book);

    void deleteBook(Long id);

}
