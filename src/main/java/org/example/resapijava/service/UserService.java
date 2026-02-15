package org.example.resapijava.service;

import org.example.resapijava.dto.user.IsCreateDto;
import org.example.resapijava.dto.user.UserCreateDto;
import org.example.resapijava.dto.user.UserDto;
import org.example.resapijava.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> getUser(Long id);
    IsCreateDto addUser(UserCreateDto user);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);
}
