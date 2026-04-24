package project.LibraryManage.dto;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {
    private Long id;

    private String name;

    private List<BookDto> books;

}
