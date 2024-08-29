package com.example.capstone3.Repository;

import com.example.capstone3.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findReservationById(int id);
}
