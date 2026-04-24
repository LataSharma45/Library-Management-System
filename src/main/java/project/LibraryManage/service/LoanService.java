package project.LibraryManage.service;

import org.springframework.data.domain.Page;
import project.LibraryManage.dto.LoanDto;
import project.LibraryManage.entity.LoanEntity;

import java.util.Optional;

public interface LoanService {
    LoanEntity saveLoan(LoanDto loanDto);
    LoanEntity loanBook(LoanEntity loan, Long userId, Long bookId) throws Exception;

    Page<LoanEntity> findAll(int page, int size, String sortBy);


    Optional<LoanEntity> findOne(Long id);

    boolean exist(Long id);

    LoanEntity partialUpdate(Long id, LoanEntity loan);

    void deleteLoan(Long id);
}
