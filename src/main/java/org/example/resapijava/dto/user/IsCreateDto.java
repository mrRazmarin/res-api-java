package org.example.resapijava.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IsCreateDto {
    private Long id;
    private String message;
    private Boolean isCreated;
}
