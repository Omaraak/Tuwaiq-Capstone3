package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Horse;
import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Repository.HorseRepository;
import com.example.capstone3.Repository.Participate_in_tournament_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseService {

    private final HorseRepository horseRepository;
    private final Participate_in_tournament_Repository participate_in_tournament_Repository;

    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    public void addHorse(Horse horse) {
        horseRepository.save(horse);
    }

    public void updateHorse(Horse horse) {
        Horse existingHorse = horseRepository.findHorseById(horse.getId());

        if (existingHorse == null) {
            throw new ApiException("Horse not found");
        }

        existingHorse.setName(horse.getName());
        existingHorse.setAge(horse.getAge());
        existingHorse.setGender(horse.getGender());
        existingHorse.setFather_name(horse.getFather_name());
        existingHorse.setGrand_father_name(horse.getGrand_father_name());
        existingHorse.setBreed(horse.getBreed());
        existingHorse.setColour(horse.getColour());
        existingHorse.setAvailable(horse.isAvailable());

        horseRepository.save(existingHorse);
    }

    public void deleteHorse(Integer id) {
        Horse horse = horseRepository.findHorseById(id);

        if (horse == null) {
            throw new ApiException("Horse not found");
        }

        horseRepository.delete(horse);
    }
    public List<Horse> getAvailableHorses() {
        return horseRepository.findHorseByAvailable(true);
    }

    public List<Horse> getHorsesByBreed(String breed) {
        return horseRepository.findHorseByBreedIgnoreCase(breed);
    }

    public List<Horse> getHorsesByAge(int age) {
        return horseRepository.findHorseByAge(age);
    }

    public List<Horse> getHorsesByColour(String colour) {
        return horseRepository.findHorseByColourIgnoreCase(colour);
    }

    public boolean isHorseAvailable(Integer id) {
        Horse horse = horseRepository.findHorseById(id);
        if (horse == null) {
            throw new ApiException("Horse not found");
        }
        return horse.isAvailable();
    }
    public double getAveragePlacementForHorse(Integer horseId) {
        Horse horse = horseRepository.findHorseById(horseId);

        if (horse == null) {
            throw new ApiException("Horse not found");
        }

        List<Participate_in_tournament> participations = participate_in_tournament_Repository.findParticipate_in_tournamentByHorse_Id(horseId);

        if (participations.isEmpty()) {
            return 0.0;
        }

        int totalPlacement = 0;
        for (Participate_in_tournament participation : participations) {
            totalPlacement += participation.getPlacement();
        }

        return (double) totalPlacement / participations.size();
    }

    public boolean isHorseQualified(Integer age){
        Horse horse1=horseRepository.findHorseByAge(age);
        if (horse1 == null) {
            throw new ApiException("Horse not found");
        }
        if(horse1.getAge()>=6){
            return true;
        }
        return false;
    }


}
