package project.LibraryManage.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import project.LibraryManage.entity.UserEntity;

import java.util.Optional;
@Service
public interface UserService {
    UserEntity saveUser(UserEntity userEntity);

    Page<UserEntity> findAll(int page, int size, String sortBy);

    Optional<UserEntity> findOne(Long id);

    Boolean isExist(Long id);

    UserEntity partialUpdate(Long id, UserEntity user);

    void deleteBook(Long id);
}
