package com.example.capstone3.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;

import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be exactly 10 digits long")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null")
    private String email;

    @NotEmpty(message = "Subscription type cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "(subscribed|unsubscribed)")
    private String subscription_type;

    @NotEmpty(message = "User type cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String user_type;

    //------------Relations---------------//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Horse> horses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Hotel> hotels;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reservation> reservations;
}
