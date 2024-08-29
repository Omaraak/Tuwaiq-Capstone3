package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty!")
    @Pattern(regexp="^[A-Za-z]*$" , message = " Name Must contain only characters no numbers")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    @NotEmpty(message = "phone should be not empty!")
    @Pattern(regexp = "(\05|0)[0-9]{9}",message = "Phone Number must start with '05' and have exactly 10 digits")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String phone;

    @NotEmpty(message = "Email should be not empty!")
    @Email(message = "Email must be valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotNull(message = "experience should be not null!")
    @Positive
    @Column(columnDefinition = "int not null")
    private int experience;

    @NotEmpty(message = "certificates should be not empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String certificates;

    @NotNull(message = "salary should be not null!")
    @Positive
    @Column(columnDefinition = "double not null")
    private double salary;

    @NotEmpty(message = "type should be not empty!")
    @Pattern(regexp="^(class training|tournament training)$",message = "Type must to be class training or tournament training")
//    @Column(columnDefinition = "varchar(20) check(type='class training' or type='tournament training')")
    private String type;

    @NotEmpty(message = "traits should be not empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String traits;

    //------------Relations---------------//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private Set<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private Set<Reservation> reservations;


}
