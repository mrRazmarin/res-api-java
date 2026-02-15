package org.example.resapijava.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
    private String login;
    private String email;
    private String password;
}
