package com.makemyrides.backend.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {
    private Long id;
    private String comment;
    private int rating;
    private Long rideId;
    private Long givenByUserId;
}
