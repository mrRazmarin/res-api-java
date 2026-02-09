package org.example.resapijava.dto.mapping;

import org.example.resapijava.dto.UserCreateDto;
import org.example.resapijava.dto.UserDto;
import org.example.resapijava.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    User toEntityCreate(UserCreateDto userCreateDto);
}
