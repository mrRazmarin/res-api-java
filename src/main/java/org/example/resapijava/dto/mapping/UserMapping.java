package org.example.resapijava.dto.mapping;

import org.example.resapijava.dto.user.UserCreateDto;
import org.example.resapijava.dto.user.UserDto;
import org.example.resapijava.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User toEntityCreate(UserCreateDto userCreateDto);
}
