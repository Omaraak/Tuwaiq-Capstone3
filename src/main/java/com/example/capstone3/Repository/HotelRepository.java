package com.example.capstone3.Repository;

import com.example.capstone3.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findHotelById(int id);
}
