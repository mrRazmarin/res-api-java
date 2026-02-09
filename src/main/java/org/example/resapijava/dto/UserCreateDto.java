package org.example.resapijava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
    private String login;
    private String email;
    private String password;
}
