package com.example.capstone3.Model;

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
public class Vet {

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

   @Min(value = 0, message = "Years of experience must be a non-negative number")
   @Column(columnDefinition = "int not null")
   private int year_of_experience;

   @NotEmpty(message = "Specialty cannot be empty")
   @Column(columnDefinition = "varchar(30) not null")
   private String specialty;

   @Min(value = 0, message = "Salary must be a non-negative value")
   @Column(columnDefinition = "double not null")
   private double salary;

   @NotEmpty(message = "location can't be empty")
   @Column(columnDefinition = "varchar(20) not null")
   private String location;

   @OneToMany(cascade = CascadeType.ALL,mappedBy = "vet")
  private Set<VetAppointment> vetAppointments;

}
