package com.example.capstone3.Repository;

import com.example.capstone3.Model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Tournament findTournamentById(Integer id);

    @Query("SELECT t FROM Tournament t WHERE t.start_tournament > ?1")
    List<Tournament> findTournamentsStartingAfter(LocalDate date);

}
