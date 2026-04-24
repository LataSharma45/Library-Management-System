package project.LibraryManage.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.LibraryManage.dto.LoanDto;
import project.LibraryManage.dto.LoanResponseDto;
import project.LibraryManage.entity.LoanEntity;

@Mapper(componentModel = "spring")
public interface LoanMapper {
//
//    @Mapping(source = "userDto", target = "users")
//    @Mapping(source = "bookDto", target = "book")
//    LoanEntity mapFrom(LoanDto loanDto);
//
//    @Mapping(source = "users", target = "userDto")
//    @Mapping(source = "book", target = "bookDto")
//    LoanDto mapTo(LoanEntity loan);
//    @Mapping(source ="user.id",target = "userId")
//    @Mapping(source ="book.id" ,target= "bookId")
//LoanResponseDto mapToResponse(LoanEntity loan);
//}

    LoanEntity mapFrom(LoanDto loanDto);

    LoanDto mapTo(LoanEntity loan);

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    LoanResponseDto mapToResponse(LoanEntity loan);
}