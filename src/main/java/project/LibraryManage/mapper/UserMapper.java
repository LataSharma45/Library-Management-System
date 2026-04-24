package project.LibraryManage.mapper;

import org.mapstruct.Mapper;
import project.LibraryManage.dto.UserDto;
import project.LibraryManage.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapFrom(UserDto userDto);
    UserDto mapTo(UserEntity userEntity);
}
