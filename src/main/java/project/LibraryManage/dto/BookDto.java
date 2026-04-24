package project.LibraryManage.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;

    private String title;

    private String isbn;

    private String genre;

    private Integer totalCopies;

    private AuthorDto authorDto;

}
