package com.example.capstone3.Repository;

import com.example.capstone3.Model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HorseRepository extends JpaRepository <Horse,Integer>{


    Horse findHorseById(Integer id);

    Horse findHorseByAge(Integer age);

    List<Horse> findHorseByAvailable(boolean available);

    List<Horse> findHorseByBreedIgnoreCase(String breed);

    List<Horse> findHorseByAge(int age);

    List<Horse> findHorseByColourIgnoreCase(String colour);

}
