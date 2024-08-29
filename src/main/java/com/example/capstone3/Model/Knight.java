package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class Knight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name can't be empty")
    @Size(min = 3, max = 13, message = "name should be between 3 and 13 char long")
    private String name;
    @NotNull(message = "age can't be empty")
    @Positive
    private int age;
    @NotNull(message = "yearsOfExperience can't be empty")
    @Positive
    private int yearsOfExperience;
    private double averagePlacement;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate licenseExpiryDate;

    //------------Relations---------------//
    @OneToMany(mappedBy = "knight", cascade = CascadeType.ALL)
    private Set<Horse> horses;

    @OneToMany(mappedBy = "knight", cascade = CascadeType.ALL)
    private Set<Participate_in_tournament> participate_in_tournaments;
}
