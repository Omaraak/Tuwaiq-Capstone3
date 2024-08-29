package com.example.capstone3.Repository;

import com.example.capstone3.Model.Stable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StableRepository extends JpaRepository<Stable, Integer> {
    Stable findStableById(int id);
}
