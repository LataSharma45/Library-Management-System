package project.LibraryManage.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.LibraryManage.dto.LoanDto;
import project.LibraryManage.dto.LoanResponseDto;
import project.LibraryManage.entity.LoanEntity;
import project.LibraryManage.mapper.LoanMapper;
import project.LibraryManage.service.LoanService;

@RestController
public class LoanController {

    private LoanMapper loanMapper;

    private LoanService loanService;

    public LoanController(LoanMapper loanMapper, LoanService loanService) {
        this.loanMapper = loanMapper;
        this.loanService = loanService;
    }

    @PutMapping("/loans/{userId}/{bookId}")
    public ResponseEntity<LoanDto>
    savedLoan (@PathVariable Long userId,
               @PathVariable Long bookId,
               @RequestBody LoanDto loanDto) throws Exception {
        LoanEntity loan =  loanMapper.mapFrom(loanDto);
        LoanEntity savedLoan = loanService.loanBook(loan,userId,bookId);
        LoanDto requestedLoan = loanMapper.mapTo(savedLoan);
        return new ResponseEntity<>(requestedLoan,HttpStatus.CREATED);
    }
@GetMapping("/loans")
    public Page<LoanResponseDto> findAllLoans (
        @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "borrowDate") String sortBy
) {
    Page<LoanEntity> findAllLoan = loanService.findAll(page, size, sortBy);
    return findAllLoan.map(loanMapper::mapToResponse);
}
    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanResponseDto> findOneLoan(@PathVariable Long id){
        return loanService.findOne(id).map(loanEntity -> {
            LoanResponseDto response = loanMapper.mapToResponse(loanEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/loans")
    public ResponseEntity<LoanResponseDto> createLoan(@RequestBody LoanDto loanDto) {
        LoanEntity savedLoan = loanService.saveLoan(loanDto);
        LoanResponseDto response = loanMapper.mapToResponse(savedLoan);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/loans/{id}")
    public ResponseEntity<LoanDto> partialUpdate(@PathVariable Long id, @RequestBody LoanDto loanDto){
        if (!loanService.exist(id))
            return ResponseEntity.notFound().build();

        LoanEntity loan = loanMapper.mapFrom(loanDto);
        LoanEntity updateLoan = loanService.partialUpdate(id,loan);
        return new ResponseEntity<>(loanMapper.mapTo(updateLoan),HttpStatus.OK);
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<LoanDto> deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
