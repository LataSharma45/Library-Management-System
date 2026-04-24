package project.LibraryManage.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import project.LibraryManage.entity.AuthorEntity;

import java.util.Optional;

public interface AuthorService {
    Boolean exist(Long id);

    AuthorEntity saveAuthor(AuthorEntity author);


    Page<AuthorEntity> findAll(int page, int size, String sortBy);

    Optional<AuthorEntity> findById(Long id);

    AuthorEntity updateAuthor(AuthorEntity author);

    AuthorEntity partialUpdate(Long id, AuthorEntity author);

    void deleteAuthor(Long id);
}

