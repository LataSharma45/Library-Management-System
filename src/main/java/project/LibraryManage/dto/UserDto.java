package project.LibraryManage.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private LocalDate memberSince;

    private List<LoanDto> loanDto;

}
