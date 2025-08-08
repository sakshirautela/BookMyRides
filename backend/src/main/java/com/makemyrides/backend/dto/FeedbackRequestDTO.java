package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDTO {
    private String comment;
    private int rating;
    private Long rideId;
    private Long givenByUserId;
}
