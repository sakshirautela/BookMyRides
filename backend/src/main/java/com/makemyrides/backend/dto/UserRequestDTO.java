package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String profile;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
