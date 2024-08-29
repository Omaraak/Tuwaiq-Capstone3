package com.example.capstone3.Repository;

import com.example.capstone3.Model.Knight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnightRepository extends JpaRepository<Knight, Integer> {
    Knight findKnightById(int id);
}
