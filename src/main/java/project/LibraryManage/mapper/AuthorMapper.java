package project.LibraryManage.mapper;

import project.LibraryManage.dto.AuthorDto;
import project.LibraryManage.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {



        AuthorEntity mapFrom(AuthorDto dto);

        AuthorDto mapTo(AuthorEntity authorEntity);

    }


