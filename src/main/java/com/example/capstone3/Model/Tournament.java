package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "tournamentName should be not Empty!")
    @Column(columnDefinition = "varchar(30) not null")
    @Pattern(regexp="^[A-Za-z]*$" , message = " tournament Name Must contain only characters no numbers")
    private String tournament_name;

    @NotNull(message = "prize should be not null!")
    @Positive
    @Column(columnDefinition = "double not null")
    private double prize ;

    @NotEmpty(message = "type should be not Empty!")
    @Pattern(regexp="^(Show jumping|racing|beauty competition)$",message = "Type must to be Show jumping or racing or beauty competition")
    //@Column(columnDefinition = "varchar(20) check(type='Show jumping' or type='racing' or type='beauty competition')")
    private String type;

    @JsonFormat(pattern ="yyyy-MM-dd")
    @PastOrPresent
    @Column(columnDefinition = "varchar(20) not null")
    private LocalDate start_tournament ;


    @JsonFormat(pattern ="yyyy-MM-dd")
    @PastOrPresent
    @Column(columnDefinition = "varchar(20) not null")
    private LocalDate end_tournament;

    @NotNull(message = "capacity should be not null!")
    @Column(columnDefinition = "int not null")
    @Positive
    private int capacity;

    @NotEmpty(message = "location should be not Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;
    @NotEmpty(message = "Status should not be empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    //------------Relations---------------//
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "organizers_id", referencedColumnName = "id")
    private Organizers organizers;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<Participate_in_tournament> participate_in_tournaments;


}
