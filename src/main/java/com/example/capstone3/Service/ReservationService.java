package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final StableRepository stableRepository;
    private final HorseRepository horseRepository;

    public void addReservation(int userID, int instructorID, int stableID, int horseID, Reservation reservation) {
        User user = userRepository.findUserById(userID);
        Instructor instructor = instructorRepository.findInstructorById(instructorID);
        Stable stable = stableRepository.findStableById(stableID);
        Horse horse = horseRepository.findHorseById(horseID);
        if (user == null || instructor == null || stable == null || horse == null) {
            throw new ApiException("Can't add reservation");
        }
        if (user.getSubscription_type().equals("subscribed")) {
            reservation.setPrice(reservation.getPrice() - reservation.getPrice() * 0.15);
        }
        horse.setAvailable(false);
        reservation.setUser(user);
        reservation.setInstructor(instructor);
        reservation.setStable(stable);
        reservation.setHorse(horse);
        userRepository.save(user);
        instructorRepository.save(instructor);
        stableRepository.save(stable);
        horseRepository.save(horse);
        reservationRepository.save(reservation);
    }

    public void cancelReservation(int userID, int reservationID) {
        User user = userRepository.findUserById(userID);
        Reservation reservation = reservationRepository.findReservationById(reservationID);
        if (user == null || reservation == null) {
            throw new ApiException("Can't cancel reservation");
        }
        if (reservation.getUser() != user) {
            throw new ApiException("Can't cancel reservation");
        }
        reservation.setStatus("canceled");
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }
    public void updateReservation (int id, Reservation reservation) throws ApiException {
        Reservation reservationToBeUpdated = reservationRepository.findReservationById(id);
        if (reservationToBeUpdated == null) {
            throw new ApiException("reservation not found");
        }
        reservationToBeUpdated.setDate(reservation.getDate());
        reservationToBeUpdated.setStatus(reservation.getStatus());
        reservationToBeUpdated.setStart_time(reservation.getStart_time());
        reservationToBeUpdated.setEnd_time(reservation.getEnd_time());
        reservationToBeUpdated.setPrice(reservation.getPrice());
        reservationRepository.save(reservationToBeUpdated);
    }
    public void deleteReservation(int id) throws ApiException {
        Reservation reservation = reservationRepository.findReservationById(id);
        if (reservation == null) {
            throw new ApiException("reservation not found");
        }
        reservationRepository.delete(reservation);
    }

}
