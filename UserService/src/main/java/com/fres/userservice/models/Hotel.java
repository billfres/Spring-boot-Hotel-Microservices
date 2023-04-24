package com.fres.userservice.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private  String hotelId;
    private  String name;
    private  String location;
    private  String about;
}
