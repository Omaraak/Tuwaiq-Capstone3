package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotEmpty
    @Pattern(regexp = "(conforme|canceled)")
    private String status;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime start_time;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime end_time;
    @Positive
    @NotNull
    private double price;

    //------------Relations---------------//
    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Instructor instructor;

    @ManyToOne
    @JsonIgnore
    private Stable stable;

    @ManyToOne
    @JsonIgnore
    private Horse horse;
}
