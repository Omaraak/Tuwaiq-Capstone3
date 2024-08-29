package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Horse;
import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Participate_in_tournament;
import com.example.capstone3.Model.Tournament;
import com.example.capstone3.Repository.HorseRepository;
import com.example.capstone3.Repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final HorseRepository horseRepository;
    private final HorseService horseService;

    public List<Tournament> getAllTournament() {
        return tournamentRepository.findAll();
    }

    public void addTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public void updateTournament(Integer id,Tournament tournament) throws ApiException {
        Tournament tournament1=tournamentRepository.findTournamentById(id);
        if (tournament1 == null) {
            throw new ApiException("Tournament not found");
        }
        tournament1.setTournament_name(tournament.getTournament_name());
        tournament1.setPrize(tournament.getPrize());
        tournament1.setType(tournament.getType());
        tournament1.setStart_tournament(tournament.getStart_tournament());
        tournament1.setEnd_tournament(tournament.getEnd_tournament());
        tournament1.setCapacity(tournament.getCapacity());
        tournament1.setLocation(tournament.getLocation());
        tournamentRepository.save(tournament1);
    }

    public void deleteTournament(Integer id) throws ApiException {
        Tournament tournament1=tournamentRepository.findTournamentById(id);
        if (tournament1 == null) {
            throw new ApiException("Tournament not found");
        }
        tournamentRepository.delete(tournament1);
    }

    public void startTournament(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }

        tournament.setStatus("Ongoing");
        tournamentRepository.save(tournament);
    }
    public void endTournament(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }


        // Update placements and horse availability as needed
        for (Participate_in_tournament participation : tournament.getParticipate_in_tournaments()) {
            Horse horse = participation.getHorse();
            horse.setAvailable(true);
            horseRepository.save(horse);
        }

        tournament.setStatus("Completed");
        tournamentRepository.save(tournament);
    }
    public void cancelTournament(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }

        if (!"Scheduled".equals(tournament.getStatus()) && !"Ongoing".equals(tournament.getStatus())) {
            throw new ApiException("Tournament cannot be cancelled at this stage");
        }

        tournament.setStatus("Cancelled");
        tournamentRepository.save(tournament);
    }

    public List<Tournament> getUpcomingTournaments() {
        LocalDate today = LocalDate.now();
        return tournamentRepository.findTournamentsStartingAfter(today);
    }

    public void rescheduleTournament(Integer tournamentId, LocalDate newStartDate, LocalDate newEndDate) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }

        if (!tournament.getStatus().equals("Scheduled")) {
            throw new ApiException("Only scheduled tournaments can be rescheduled");
        }

        tournament.setStart_tournament(newStartDate);
        tournament.setEnd_tournament(newEndDate);
        tournamentRepository.save(tournament);
    }

    public int checkTournamentCapacity(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        if (tournament == null) {
            throw new ApiException("Tournament not found");
        }

        return tournament.getCapacity();
    }

    public Horse darkHorse(int tournamentID) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentID);
        if (tournament == null || tournament.getParticipate_in_tournaments() == null || tournament.getParticipate_in_tournaments().isEmpty()) {
            throw new ApiException("Horse not found");
        }
        double min = 10000;
        Horse darkHorse = null;
        for (Participate_in_tournament participation : tournament.getParticipate_in_tournaments()) {
            if (horseService.getAveragePlacementForHorse(participation.getHorse().getId()) < min){
                darkHorse = participation.getHorse();
                min = horseService.getAveragePlacementForHorse(participation.getHorse().getId());
            }
        }
        return darkHorse;
    }

    public Horse favoriteToWin(int tournamentID) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentID);
        if (tournament == null || tournament.getParticipate_in_tournaments() == null || tournament.getParticipate_in_tournaments().isEmpty()) {
            throw new ApiException("Horse not found");
        }
        double max = 0;
        Horse favoriteToWin = null;
        for (Participate_in_tournament participation : tournament.getParticipate_in_tournaments()) {
            if (horseService.getAveragePlacementForHorse(participation.getHorse().getId()) > max){
                favoriteToWin = participation.getHorse();
                max = horseService.getAveragePlacementForHorse(participation.getHorse().getId());
            }
        }
        return favoriteToWin;
    }





}
