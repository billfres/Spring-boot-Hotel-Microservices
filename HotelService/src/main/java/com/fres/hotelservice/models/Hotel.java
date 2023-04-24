package com.fres.hotelservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hotel {

    @Id
    private  String hotelId;
    private  String name;
    private  String location;
    private  String about;
/*
    @Transient
    private List<Rating> ratings = new ArrayList<>();*/
}
