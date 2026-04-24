package project.LibraryManage.mapper;

import org.mapstruct.Mapper;
import project.LibraryManage.dto.BookDto;
import project.LibraryManage.entity.BookEntity;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity mapFrom(BookDto bookDto);
    BookDto mapTo(BookEntity bookEntity);
}

