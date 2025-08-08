package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private Long userId;
    private int seats;
}
