package com.microservice.RatingService.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "micro_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    private String ratingId;
    private int rating;
    private String feedback;
    private String userId;
    private String hotelId;

}
