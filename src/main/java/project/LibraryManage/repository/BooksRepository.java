package project.LibraryManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.LibraryManage.entity.BookEntity;

@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long> {
}
