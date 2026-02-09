package org.example.resapijava.controller;

import org.example.resapijava.dto.IsCreateDto;
import org.example.resapijava.dto.UserCreateDto;
import org.example.resapijava.dto.UserDto;
import org.example.resapijava.dto.UserUpdateDto;
import org.example.resapijava.service.serviceimpl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    public UserController (UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", name = "Get user by id endpoint")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable(required = true) Long id
    ) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/users", name = "Get users by endpoint")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        Page<UserDto> usersPage = userService.getAllUsers(pageable);

        return ResponseEntity.ok(usersPage);
    }

    @PostMapping(path = "/create/user", name = "Create user")
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

    @PutMapping(path = "/change/{id}", name = "Change user data")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDto userUpdateDto
    ) {
        UserDto response = userService.updateUser(id, userUpdateDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
