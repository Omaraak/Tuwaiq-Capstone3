package com.example.capstone3.Repository;

import com.example.capstone3.Model.VetAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VetAppointmentRepository extends JpaRepository<VetAppointment, Integer> {
    VetAppointment findVetAppointmentById(int id);

    List<VetAppointment>findVetAppointmentByHorseId(Integer horseId);

}
