package com.fres.hotelservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    //@Id
    // @Column(name = "ID")
    private  String ratingId;
    private  String userId;
    private  String hotelId;
    private int rating;
    private  String feedback;
}
