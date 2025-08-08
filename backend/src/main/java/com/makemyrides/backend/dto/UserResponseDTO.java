package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String profile;
    private String name;
    private String email;
    private String phoneNumber;
}
