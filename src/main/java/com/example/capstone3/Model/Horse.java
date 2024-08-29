package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotEmpty(message = "Gender cannot be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String gender;

    @NotEmpty(message = "Father's name cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String father_name;

    @NotEmpty(message = "Grandfather's name cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String grand_father_name;

    @NotEmpty(message = "Breed cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String breed;

    @NotEmpty(message = "Colour cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String colour;

    @Column(columnDefinition = "boolean not null")
    private boolean available;


    //------------Relations---------------//

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "horse")
    private Set<VetAppointment> vetAppointments;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horse")
    @JsonIgnore
    private Set<Reservation> reservations;

    @ManyToOne
    @JsonIgnore
    private Hotel hotel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "knight_id", referencedColumnName = "id")
    private Knight knight;

    @OneToMany(mappedBy = "horse", cascade = CascadeType.ALL)
    private Set<Participate_in_tournament> participate_in_tournaments;
}
