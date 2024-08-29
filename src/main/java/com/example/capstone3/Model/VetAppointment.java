package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VetAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime start_time;
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull
    private LocalTime end_time;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String status;
    private int price;

    @ManyToOne
    @JoinColumn(name = "vet_id",referencedColumnName = "id")
    @JsonIgnore
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "stable_id",referencedColumnName = "id")
    @JsonIgnore
    private Stable stable;

    @ManyToOne
    @JoinColumn(name = "horse_id",referencedColumnName = "id")
    @JsonIgnore
    private Horse horse;

}
