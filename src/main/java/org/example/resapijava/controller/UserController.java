package org.example.resapijava.controller;

import org.example.resapijava.dto.user.IsCreateDto;
import org.example.resapijava.dto.user.UserCreateDto;
import org.example.resapijava.dto.user.UserDto;
import org.example.resapijava.dto.user.UserUpdateDto;
import org.example.resapijava.endpoints.ApiEndpoints;
import org.example.resapijava.service.serviceimpl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.API_V1)
@Validated
public class UserController {

    private final UserServiceImpl userService;

    public UserController (UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = ApiEndpoints.USER_ID, name = "Get user by id endpoint")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable(required = true) Long id
    ) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = ApiEndpoints.USERS, name = "Get users by endpoint")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        Page<UserDto> usersPage = userService.getAllUsers(pageable);

        return ResponseEntity.ok(usersPage);
    }

    @PostMapping(path = ApiEndpoints.USER_CREATE, name = "Create user")
    public ResponseEntity<IsCreateDto> createUser(
            @RequestBody UserCreateDto userCreateDto
    ) {
        IsCreateDto response = userService.addUser(userCreateDto);

        if (response.getIsCreated()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = ApiEndpoints.USER_CHANGE, name = "Change user data")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDto userUpdateDto
    ) {
        UserDto response = userService.updateUser(id, userUpdateDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
