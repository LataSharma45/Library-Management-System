package project.LibraryManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.LibraryManage.dto.LoanDto;
import project.LibraryManage.entity.BookEntity;
import project.LibraryManage.entity.LoanEntity;
import project.LibraryManage.entity.UserEntity;
import project.LibraryManage.exception.BookNotFoundException;
import project.LibraryManage.exception.UserNotFoundException;
import project.LibraryManage.mapper.BookMapper;
import project.LibraryManage.mapper.LoanMapper;
import project.LibraryManage.repository.BooksRepository;
import project.LibraryManage.repository.LoanRepository;
import project.LibraryManage.repository.UserRepository;
import project.LibraryManage.service.LoanService;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UserRepository userRepository;
@Autowired
private BookMapper bookMapper;
@Autowired
private  LoanMapper loanMapper;

    @Override
    public LoanEntity saveLoan(LoanDto loanDto) {

        if (loanDto.getUserDto() == null || loanDto.getUserDto().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        if (loanDto.getBookDto() == null || loanDto.getBookDto().getId() == null) {
            throw new RuntimeException("Book ID is required");
        }

        UserEntity user = userRepository.findById(loanDto.getUserDto().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BookEntity book = booksRepository.findById(loanDto.getBookDto().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        LoanEntity loan = new LoanEntity();
        loan.setUsers(user);
        loan.setBook(book);
        loan.setBorrowDate(loanDto.getBorrowDate());
        loan.setDueDate(loanDto.getDueDate());
        loan.setReturned(loanDto.getReturned());
        loan.setFine(loanDto.getFine());

        return loanRepository.save(loan);
    }

    @Override
    public LoanEntity loanBook(LoanEntity loan, Long userId, Long bookId) throws Exception {
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        var book = booksRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));

        if (book.getTotalCopies() <= 0) {
            throw new BookNotFoundException("No copies available for book: " + book.getTitle());
        }

        loan.setUsers(user);
        loan.setBook(book);
        loan.setBorrowDate(LocalDate.now());

        book.setTotalCopies(book.getTotalCopies() - 1);
        booksRepository.save(book);
        return loanRepository.save(loan);
    }


    @Override
    public Page<LoanEntity> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return loanRepository.findAll(pageable);
    }

    @Override
    public Optional<LoanEntity> findOne(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public boolean exist(Long id) {
        return loanRepository.existsById(id);
    }

    @Override
    public LoanEntity partialUpdate(Long id, LoanEntity loan) {
        loan.setId(id);
        return loanRepository.findById(id).map(loanExist->{
            Optional.ofNullable(loan.getBorrowDate()).ifPresent(loanExist::setBorrowDate);
            Optional.ofNullable(loan.getReturned()).ifPresent(loanExist::setReturned);
            Optional.ofNullable(loan.getFine()).ifPresent(loanExist::setFine);
            Optional.ofNullable(loan.getDueDate()).ifPresent(loanExist::setDueDate);
            return loanRepository.save(loanExist);
        }).orElseThrow(()-> new RuntimeException("loan instance was not found"));
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }


}
