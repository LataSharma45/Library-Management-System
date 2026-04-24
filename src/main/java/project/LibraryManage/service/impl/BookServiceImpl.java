package project.LibraryManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.LibraryManage.dto.BookDto;
import project.LibraryManage.entity.AuthorEntity;
import project.LibraryManage.entity.BookEntity;
import project.LibraryManage.mapper.BookMapper;
import project.LibraryManage.repository.AuthorRepository;
import project.LibraryManage.repository.BooksRepository;
import project.LibraryManage.service.BookService;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookDto saveBook(BookDto book) {
        AuthorEntity author = authorRepository.findById(book.getAuthorDto().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setTotalCopies(book.getTotalCopies());
        bookEntity.setAuthorEntity(author);

        BookEntity saved = booksRepository.save(bookEntity);

        return bookMapper.mapTo(saved);
    }

    @Override
    public Page<BookEntity> findAll(int page, int size, String sortBy) {
        Pageable pageable =  PageRequest.of(page,size, Sort.by(sortBy));
        return booksRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(Long id) {
        return booksRepository.findById(id);
    }

    @Override
    public boolean exist(Long id) {
        return booksRepository.existsById(id);
    }

    @Override
    public BookDto updateBook(Long id, BookDto book) {
        book.setId(id);

        AuthorEntity author = authorRepository.findById(book.getAuthorDto().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        BookEntity savedBook = new BookEntity();
        savedBook.setId(book.getId());
        savedBook.setTitle(book.getTitle());
        savedBook.setIsbn(book.getIsbn());
        savedBook.setGenre(book.getGenre());
        savedBook.setTotalCopies(book.getTotalCopies());
        savedBook.setAuthorEntity(author);
        BookEntity requestBook =  booksRepository.save(savedBook);

        return bookMapper.mapTo(requestBook);
    }

    @Override
    public BookEntity partialUpdate(Long id, BookEntity book) {
        book.setId(id);
        return booksRepository.findById(id).map(bookExist ->{
            Optional.ofNullable(book.getTitle()).ifPresent(bookExist::setTitle);
            Optional.ofNullable(book.getIsbn()).ifPresent(bookExist::setIsbn);
            Optional.ofNullable(book.getGenre()).ifPresent(bookExist::setGenre);
            Optional.ofNullable(book.getTotalCopies()).ifPresent(bookExist::setTotalCopies);
            return booksRepository.save(bookExist);
        }).orElseThrow(()-> new RuntimeException("Book detail was not found"));
    }

    @Override
    public void deleteBook(Long id) {
        booksRepository.deleteById(id);
    }
}


