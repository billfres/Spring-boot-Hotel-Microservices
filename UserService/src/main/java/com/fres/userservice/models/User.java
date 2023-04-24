package com.fres.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    private  String userId;

    @Column(name = "NAME", length = 20)
    private  String name;

    @Column(name = "EMAIL")
    private  String email;

    @Column(name = "ABOUT")
    private  String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
