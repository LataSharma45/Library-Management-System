package project.LibraryManage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanDto {
    private Long id;

    private UserDto userDto;

    private BookDto bookDto;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private Boolean returned;

    private LocalDate dueDate;

    private Double fine;

}
