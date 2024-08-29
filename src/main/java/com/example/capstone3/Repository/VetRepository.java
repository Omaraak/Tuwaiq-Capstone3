package com.example.capstone3.Repository;

import com.example.capstone3.Model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<Vet,Integer> {

    Vet findVetById(Integer id);

    List<Vet> findVetByLocation(String location);

    List<Vet> findVetBySalary(double salary);

    List<Vet> findVetBySpecialty(String specialty);
    @Query("select v from Vet v where v = ?1")
    List<Vet> findVetByYear_of_experience(Integer year_of_experience);

}
