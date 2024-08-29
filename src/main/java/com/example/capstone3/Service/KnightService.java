package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Horse;
import com.example.capstone3.Model.Knight;
import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Model.Tournament;
import com.example.capstone3.Repository.HorseRepository;
import com.example.capstone3.Repository.KnightRepository;
import com.example.capstone3.Repository.Participate_in_tournament_Repository;
import com.example.capstone3.Repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KnightService {
    private final KnightRepository knightRepository;
    private final HorseRepository horseRepository;
    private final TournamentRepository tournamentRepository;
    private final Participate_in_tournamentService participate_in_tournamentService;
    private final Participate_in_tournament_Repository participate_in_tournament_Repository;

    public List<Knight> getAllKnight() {
        return knightRepository.findAll();
    }

    public void addKnight(Knight knight) {
        knightRepository.save(knight);
    }

    public void updateKnight(Integer id,Knight knight) throws ApiException {
        Knight knight1 = knightRepository.findKnightById(id);
        if (knight1 == null) {
            throw new ApiException("Knight not found");
        }
        knight1.setName(knight.getName());
        knight1.setAge(knight.getAge());
        knight1.setYearsOfExperience(knight.getYearsOfExperience());
        knight1.setAveragePlacement(knight.getAveragePlacement());
        knight1.setLicenseExpiryDate(knight.getLicenseExpiryDate());
        knightRepository.save(knight1);

    }

    public void deleteKnight(Integer id) throws ApiException {
        Knight knight1 = knightRepository.findKnightById(id);
        if (knight1 == null) {
            throw new ApiException("Knight not found");
        }
        knightRepository.delete(knight1);
    }

    public void assignHorseToKnight (Integer horse_id, Integer knight_id) {
        Horse horse = horseRepository.findHorseById(horse_id);
        if (horse == null) {
            throw new ApiException("horse not found");
        }
        Knight knight = knightRepository.findKnightById(knight_id);
        if (knight == null) {
            throw new ApiException("knight not found");
        }
        horse.setKnight(knight);
        horseRepository.save(horse);

    }
    public void registerTournament (Integer knight_id, Integer tournament_id,Integer horse_id) {

        Knight knight = knightRepository.findKnightById(knight_id);
        if (knight == null) {
            throw new ApiException("knight not found");
        }
        Tournament tournament = tournamentRepository.findTournamentById(tournament_id);
        if (tournament == null) {
            throw new ApiException("tournament not found");
        }
        Horse horse = horseRepository.findHorseById(horse_id);
        if (horse == null) {
            throw new ApiException("horse not found");
        }

        Boolean checkHorse = false;
        for (Horse h : knight.getHorses()) {
            if (h.getId().equals(horse_id)) {
                checkHorse = true;
            }
        }
        if (!checkHorse) {
            throw new ApiException("horse is not linked to the knight");
        }
        if (!horse.isAvailable()){
            throw new ApiException("horse is not available");
        }

        if (tournament.getCapacity()<=0){
            throw new ApiException("The tournament is full");
        }

        Participate_in_tournament participation = new Participate_in_tournament();
        participation.setPlacement(0);
        participation.setPrize(0.0);
        participation.setHorse(horse);
        participation.setKnight(knight);
        participation.setTournament(tournament);

        participate_in_tournamentService.addParticipation(participation);

        tournament.setCapacity(tournament.getCapacity()-1);
        tournamentRepository.save(tournament);

        horse.setAvailable(false);
        horseRepository.save(horse);

        knightRepository.save(knight);

    }
    public List<Participate_in_tournament> getTournamentHistoryForKnight(Integer knightId) {
        return participate_in_tournament_Repository.findParticipate_in_tournamentByKnight_Id(knightId);
    }

    public double getAveragePlacementForKnight(Integer knightId) {
        Knight knight = knightRepository.findKnightById(knightId);

        if (knight == null) {
            throw new ApiException("Knight not found");
        }

        List<Participate_in_tournament> participations =
                participate_in_tournament_Repository.findParticipate_in_tournamentByKnight_Id(knightId);

        if (participations.isEmpty()) {
            return 0.0;
        }

        int totalPlacement = 0;
        for (Participate_in_tournament participation : participations) {
            totalPlacement += participation.getPlacement();
        }

        return (double) totalPlacement / participations.size();
    }

    public double getTotalEarningsForKnight(Integer knightId) {
        Knight knight = knightRepository.findKnightById(knightId);

        if (knight == null) {
            throw new ApiException("Knight not found");
        }

        List<Participate_in_tournament> participations = participate_in_tournament_Repository.findParticipate_in_tournamentByKnight_Id(knightId);

        double totalEarnings = 0.0;
        for (Participate_in_tournament participation : participations) {
            totalEarnings += participation.getPrize();
        }

        return totalEarnings;
    }


}
