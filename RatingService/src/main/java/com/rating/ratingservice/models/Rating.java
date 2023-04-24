package com.rating.ratingservice.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_ratings")
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private  Long ratingId;
    private  String userId;
    private  String hotelId;
    private int rating;
    private  String feedback;
}
