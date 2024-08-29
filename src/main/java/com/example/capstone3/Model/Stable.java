package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 20)
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int capacity;
    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int area_size;
    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int number_of_houres;

    @NotEmpty(message = "location can't be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String location;

    //------------Relations---------------//
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "stable")
    private Set<VetAppointment> vetAppointments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stable")
    private Set<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stable")
    private Set<Hotel> hotels;
}
