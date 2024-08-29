package com.example.capstone3.Model;

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
public class Organizers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty!")
    @Pattern(regexp="^[A-Za-z]*$" , message = "Name Must contain only characters no numbers")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    @NotEmpty(message = "Email should be not empty!")
    @Email(message = "Email must be valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;


    @NotNull(message = "number of tournament hosted should be not null!")
    @Positive
    @Column(columnDefinition = "int not null")
    private int number_of_tournament_hosted;

    //------------Relations---------------//
    @OneToMany(mappedBy = "organizers", cascade = CascadeType.ALL)
    private Set<Tournament> tournaments;

}
