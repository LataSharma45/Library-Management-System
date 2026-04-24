package project.LibraryManage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.LibraryManage.dto.LoanDto;
import project.LibraryManage.dto.LoanResponseDto;
import project.LibraryManage.entity.LoanEntity;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanEntity mapFrom(LoanDto loanDto);
    LoanDto mapTo(LoanEntity loan);

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    LoanResponseDto mapToResponse(LoanEntity loan);
}