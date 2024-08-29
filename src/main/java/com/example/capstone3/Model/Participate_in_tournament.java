package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participate_in_tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private int placement;

    @PositiveOrZero(message = "Prize must be a non-negative value")
    @Column(columnDefinition = "double not null")
    private double prize;

    //------------Relations---------------//
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "knight_id", referencedColumnName = "id")
    private Knight knight;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "horse_id", referencedColumnName = "id")
    private Horse horse;
}
